package br.com.delogic.csa.repository.sql;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import br.com.delogic.csa.repository.Criteria;
import br.com.delogic.csa.repository.QueryRepository;
import br.com.delogic.csa.repository.RepositoryData;
import br.com.delogic.jfunk.Has;

/**
 * A {@code Query} represents a SQL wrapped component which allows to execute
 * "queries" and retrieve information from databases, formatted accordingly to
 * the mapped type. A {@code Query} can be create in an XML file our coded into
 * a Java class (less desirable). Keep in mind the Query object is a stateless
 * and reusable component which can be {@code @Inject}-ed into another services. <br>
 * <br>
 * After creating a {@code Query} you must provide at least the "select" and
 * "from" statements and may also provide other features like "where", "groupBy"
 * and "orderBy". Check on respective method documentation to know more about
 * their responsibilities. <br>
 * <br>
 * The field "and" is a map of String,String which maps the parameter name with
 * the statement to be inserted as an "and" filter to the end of the "where"
 * statement.<br>
 * <br>
 * The field "orders" is similar to the field "and" except it'll append
 * "order by" statements to the end of the {@code Query}. <br>
 * <br>
 * The field "returnType" is required so {@code Query} objects can map the
 * result values into List<T> of the referenced type. The type can be a single
 * column like java.lang.String or java.lang.Integer, but can be also a POJO
 * with attributes, in which case it will map the attributes names from the POJO
 * to the columns names from the Query (case independent). This way the
 * {@code Query} object will return List<POJO>s of populated desired objects. <br>
 * <br>
 * A regular XML query creation will be similar to the example below.
 *
 * <pre>
 *  &lt;bean id=&quot;userQuery&quot; class=&quot;br.com.delogic.yesql.Query&quot;
 *    p:select=&quot;u.id, u.name, u.birthDate&quot;
 *    p:from=&quot;users u&quot;
 *    p:returnType=&quot;mycompany.User&quot;&gt;
 *    &lt;property name=&quot;and&quot;&gt;
 *       &lt;map&gt;
 *          &lt;entry key=&quot;name:text&quot; value=&quot;u.name like :name&quot; /&gt;
 *          &lt;entry key=&quot;userId:number&quot; value=&quot;t.id = :userId&quot; /&gt;
 *       &lt;/map&gt;
 *    &lt;/property&gt;
 *    &lt;property name=&quot;orders&quot;&gt;
 *       &lt;map&gt;
 *          &lt;entry key=&quot;name&quot; value=&quot;name, id&quot; /&gt;
 *          &lt;entry key=&quot;BIRTH_DATE&quot; value=&quot;birthDate, name, id&quot; /&gt;
 *       &lt;/map&gt;
 *    &lt;/property&gt;
 * &lt;/bean&gt;
 *
 * <pre>
 * For more information check each method and the documentation online.
 *
 * @author celio@delogic.com.br
 *
 * @param <T>
 * - Type of the returnType parameter required.
 */
public class SqlQuery<T> implements InitializingBean, QueryRepository<T> {

    /**
     * Querie's select statement without the "select" reserved word
     */
    private String                              select;

    /**
     * Querie's from statement without the "from" reserved word
     */
    private String                              from;

    /**
     * Querie's where statement without the "where" reserved word
     */
    private String                              where;

    /**
     * Querie's group by statement without the "group by" reserved word
     */
    private String                              groupBy;

    /**
     * Querie's order by statement without the "order by" reserved word
     */
    private String                              orderBy;

    /**
     * List of possible "and" statements which will be appended according to the
     * parameters sent
     */
    private Map<String, String>                 and;

    /**
     * List of possible "order by" statements which will be appened according to
     * the parameters sent
     */
    private Map<String, String>                 orders;

    /**
     * This Query return type. Will be used to map the {@code ResultSet} vlaues
     * to the appropriate fields
     */
    private Class<T>                            returnType;

    /*
     * Internal control of parameter types
     */
    private Map<String, PermittedParameterType> registeredParameters   = new HashMap<String, SqlQuery.PermittedParameterType>();

    /*
     * Internal control of p
     */
    private Map<String, PermittedParameterType> mandatoryParameters;

    private static final String                 ORDER_STATEMENT        = " order by ";
    private static final String                 SELECT_STATEMENT       = "select ";
    private static final String                 SELECT_COUNT_STATEMENT = "select count(*) ";
    private static final String                 FROM_STATEMENT         = " from ";
    private static final String                 WHERE_STATEMENT        = " where ";
    private static final String                 GROUP_STATEMENT        = " group by ";
    private static final String                 AND_OPERATOR           = " and ";

    private NamedParameterJdbcTemplate          template;
    private RowMapper<T>                        rowMapper;

    @Inject
    private SqlQueryRangeBuilder                rangeBuilder;

    @Inject
    private DataSource                          dataSource;

    private boolean                             initialized            = false;

    /**
     * Logger using slf4j facade
     */
    private static final Logger                 logger                 = LoggerFactory.getLogger(SqlQuery.class);

    /**
     * Will initialize the {@code Query} object using the required statements:
     * select, from; the required parameter: returnType and a datasource. It'll
     * run only once before any call to retrieve information from database or
     * explicitly calling {@code afterPropertiesSet()} method.
     */
    void maybeInitializeQuery() {
        if (initialized) {
            /*
             * if is already initialized just leave
             */
            return;
        }

        Assert.hasText(select, "Select statement is mandatory");
        Assert.hasText(from, "From statement is mandatory");
        Assert.notNull(returnType, "ReturnType is mandatory");
        Assert.notNull(dataSource, "DataSource is mandatory");

        template = new NamedParameterJdbcTemplate(dataSource);

        mandatoryParameters = new HashMap<String, SqlQuery.PermittedParameterType>();

        /*
         * search and remove the mandatory parameters from mandatory statements
         */
        Pattern pattern = Pattern.compile(":\\w+:\\w+");
        select = removeMandatoryParamType(select, pattern, mandatoryParameters);
        where = removeMandatoryParamType(where, pattern, mandatoryParameters);
        orderBy = removeMandatoryParamType(orderBy, pattern, mandatoryParameters);
        groupBy = removeMandatoryParamType(groupBy, pattern, mandatoryParameters);

        /*
         * search and remove the mandatory parameters from orders statements
         * with parameters
         */
        if (Has.content(orders)) {
            for (Entry<String, String> entry : orders.entrySet()) {
                entry.setValue(removeMandatoryParamType(entry.getValue(), pattern, mandatoryParameters));
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Mandatory parameters:" + mandatoryParameters);
        }

        /*
         * Create appropriate rowmappers to convert the values from the
         * ResultSet
         */
        if (returnType.equals(String.class)) {
            rowMapper = new StringRowMapper<T>();

        } else if (returnType.equals(Integer.class)) {
            rowMapper = new IntegerRowMapper<T>();

        } else if (returnType.equals(Long.class)) {
            rowMapper = new LongRowMapper<T>();

        } else {
            rowMapper = BeanPropertyRowMapper.newInstance(returnType);
        }

        /*
         * sets it as initialized
         */
        initialized = true;
    }

    /**
     * Removes the parameters included into mandatory statements given the
     * pattern and the statement
     *
     * @param statement
     * @param pattern
     * @param params
     * @return
     */
    private String removeMandatoryParamType(String statement, Pattern pattern, Map<String, PermittedParameterType> params) {
        if (!Has.content(statement) || !statement.contains(":")) {
            return statement;
        }
        Matcher matcher = pattern.matcher(statement);
        while (matcher.find()) {
            String param = matcher.group();
            String[] paramSplitted = param.split(":");
            params.put(paramSplitted[1], PermittedParameterType.fromName(paramSplitted[2]));
            statement = statement.replace(":" + paramSplitted[2], "");
        }
        return statement;
    }

    /**
     * Returns a list of data from the database, already mapped to the
     * appropriate object(s). It may take a queryParameters parameter or null.
     *
     * @param queryParameters
     *            - a map of parameters which will be added according to the
     *            parameters configured in the query
     * @return List of desired database values mapped into objects
     */
    public List<T> getList(Criteria queryParameters) {
        maybeInitializeQuery();
        if (returnType == null) {
            throw new IllegalStateException("returnType cannot be null when getting a list with type mapping");
        }
        Map<String, Object> params = queryParameters != null && queryParameters.getParameters() != null ? queryParameters.getParameters()
                                                                                                       : new HashMap<String, Object>();
        String composedQuery = composeQuery(queryParameters);

        if (queryParameters != null && (queryParameters.getStartRow() != null || queryParameters.getEndRow() != null)) {
            composedQuery = rangeBuilder.buildRangeQuery(composedQuery, queryParameters);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Executing query:" + composedQuery.replaceAll("\\s+", " "));
            logger.debug("With parameters:"
                + (queryParameters != null && queryParameters.getParameters() != null ? queryParameters.getParameters() : ""));
        }

        List<T> data = null;

        data = template.query(composedQuery, params, rowMapper);

        if (logger.isDebugEnabled()) {
            logger.debug(data.size() + " itens found");
        }

        return data;

    }

    /**
     * Returns a list of data from the database, already mapped to the
     * appropriate object(s).
     *
     * @return List of desired database values mapped into objects
     */
    public List<T> getList() {
        return getList(null);
    }

    /**
     * Returns only the first row of the database given the parameters, sorting
     * and other configurations. This is useful when querying for objects by id
     * which only the first item is relevant and will be returned.
     *
     * @param queryParameters
     *            - a map of parameters which will be added according to the
     *            parameters configured in the query
     * @return T The first item POJO mapped found
     */
    public T getFirst(Criteria queryParameters) {
        if (returnType == null) {
            throw new IllegalStateException("returnType cannot be null when getting a result with type mapping");
        }
        Map<String, Object> params = queryParameters != null && queryParameters.getParameters() != null ? queryParameters.getParameters()

                                                                                                       : new HashMap<String, Object>();
        // getting only the first result
        queryParameters.setStartRow(0L);
        queryParameters.setEndRow(1L);

        String composedQuery = composeQuery(queryParameters);

        if (queryParameters != null && (queryParameters.getStartRow() != null || queryParameters.getEndRow() != null)) {
            composedQuery = rangeBuilder.buildRangeQuery(composedQuery, queryParameters);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Executing query:" + composedQuery.replaceAll("\\s+", " "));
            logger.debug("With parameters:"
                + (queryParameters != null && queryParameters.getParameters() != null ? queryParameters.getParameters() : ""));
        }

        List<T> data = null;

        data = template.query(composedQuery, params, rowMapper);

        if (logger.isDebugEnabled()) {
            logger.debug(data.size() + " itens found");
        }

        if (data.isEmpty()) {
            return null;
        }

        return data.get(0);

    }

    /**
     * Returns the amount of items found on database given the parameters.
     *
     * @param queryParameters
     *            - a map of parameters which will be added according to the
     *            parameters configured in the query
     * @return Number of rows found on database
     */
    public long count(Criteria queryParameters) {

        Map<String, Object> params = queryParameters != null && queryParameters.getParameters() != null ? queryParameters.getParameters()
                                                                                                       : new HashMap<String, Object>();
        String composedQuery = composeCount(queryParameters);

        if (logger.isDebugEnabled()) {
            logger.debug("Executando consulta:" + composedQuery.replaceAll("\\s+", " "));
            logger.debug("Com parametros:"
                + (queryParameters != null && queryParameters.getParameters() != null ? queryParameters.getParameters() : ""));
        }

        long count = template.queryForLong(composedQuery, params);

        if (logger.isDebugEnabled()) {
            logger.debug("Total de itens encontrados:" + count);
        }

        return count;
    }

    /**
     * Returns the amount of items found on database.
     *
     * @return Number of rows found on database
     */
    public long count() {
        return count(null);
    }

    /**
     * Appends all the mandatory statements with the query parameters
     * statements, orders and group by statements into a single query to be
     * executed against the database to return the data values.
     *
     * @param queryParameters
     *            - a map of parameters which will be added according to the
     *            parameters configured in the query
     * @return String with the query composed and ready for execution
     */
    String composeQuery(Criteria queryParameters) {
        StringBuilder sbQuery = new StringBuilder(SELECT_STATEMENT + select + FROM_STATEMENT + from);
        if (Has.content(where)) {
            sbQuery.append(WHERE_STATEMENT);
            sbQuery.append(where);
        }

        Map<String, Object> params = queryParameters != null && Has.content(queryParameters.getParameters()) ? queryParameters
            .getParameters()
                                                                                                            : null;

        if (Has.content(and) && Has.content(params)) {

            sbQuery.append(!Has.content(where) ? WHERE_STATEMENT : AND_OPERATOR);

            for (Iterator<String> it =
                params.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                if (!and.containsKey(key)) continue;

                sbQuery.append(and.get(key));

                if (it.hasNext()) sbQuery.append(AND_OPERATOR);
            }
            // if the query ends with where or and statements we need to remove
            // them.
            String q = sbQuery.toString();
            if (q.endsWith(WHERE_STATEMENT)) {
                q = q + "tbr";
                q = q.replace(WHERE_STATEMENT + "tbr", "");
            } else if (q.endsWith(AND_OPERATOR)) {
                q = q + "tbr";
                q = q.replace(AND_OPERATOR + "tbr", "");
            }
            sbQuery = new StringBuilder(q);
        }
        if (Has.content(groupBy)) {
            sbQuery.append(GROUP_STATEMENT).append(groupBy);
        }

        if (queryParameters == null || (!Has.content(queryParameters.getOrderByKey()) && !Has.content(orders))) {
            if (Has.content(orderBy)) {
                sbQuery.append(ORDER_STATEMENT).append(orderBy);
            }
        } else if (queryParameters != null && Has.content(queryParameters.getOrderByKey()) && Has.content(orders)) {
            String orderStatement = "";
            for (String orderByKey : queryParameters.getOrderByKey()) {
                orderStatement += orders.containsKey(orderByKey) ? (!Has.content(orderStatement) ? "" : ",")
                    + orders.get(orderByKey) : "";
            }
            if (Has.content(orderStatement)) {
                sbQuery.append(ORDER_STATEMENT + orderStatement);
            }
        }
        return sbQuery.toString();
    }

    /**
     * Appends all the mandatory statements with the query parameters
     * statements, orders and group by statements into a single count query to
     * be executed against the database to return the amount of itens.
     *
     * @param queryParameters
     *            - a map of parameters which will be added according to the
     *            parameters configured in the query
     * @return String with the query composed and ready for execution
     */
    String composeCount(Criteria queryParameters) {
        StringBuilder sbQuery = new StringBuilder(SELECT_COUNT_STATEMENT + FROM_STATEMENT + "(" + SELECT_STATEMENT + select
            + FROM_STATEMENT + from);
        if (Has.content(where)) {
            sbQuery.append(WHERE_STATEMENT);
            sbQuery.append(where);
        }

        Map<String, Object> params = queryParameters != null && Has.content(queryParameters.getParameters()) ? queryParameters
            .getParameters()
                                                                                                            : null;

        if (Has.content(and) && Has.content(params)) {

            sbQuery.append(!Has.content(where) ? WHERE_STATEMENT : AND_OPERATOR);

            for (Iterator<String> it =
                params.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                if (!and.containsKey(key)) continue;

                sbQuery.append(and.get(key));

                if (it.hasNext()) sbQuery.append(AND_OPERATOR);
            }
            // if the query ends with where or and statements we need to remove
            // them.
            String q = sbQuery.toString();
            if (q.endsWith(WHERE_STATEMENT)) {
                q = q + "tbr";
                q = q.replace(WHERE_STATEMENT + "tbr", "");
            } else if (q.endsWith(AND_OPERATOR)) {
                q = q + "tbr";
                q = q.replace(AND_OPERATOR + "tbr", "");
            }
            sbQuery = new StringBuilder(q);
        }
        if (Has.content(groupBy)) {
            sbQuery.append(GROUP_STATEMENT).append(groupBy);
        }

        return sbQuery.toString() + ") sql_query_count_table";
    }

    /**
     * Parses the numeric order by statements into correct order by changing the
     * "-" (minus) sign by desc operations
     *
     * @param orderBy
     *            - array of orders to be executed for this query.
     * @return String containnig the ordering in appropriate format
     */
    String parseOrderBy(String[] orderBy) {
        String[] orders = orderBy;
        String newOrderBy = " ";
        boolean addComma = false;
        for (String s : orders) {
            int columnIndex = Integer.parseInt(s);
            if (addComma) newOrderBy += " , ";
            if (columnIndex < 0) {
                columnIndex = Math.abs(columnIndex);
                newOrderBy += (columnIndex + " desc");
            } else {
                newOrderBy += columnIndex;
            }
            addComma = true;
        }
        return newOrderBy;
    }

    /**
     * Returns the "and" statements of this {@code Query}
     *
     * @return Map with all the statements
     */
    public Map<String, String> getAnd() {
        return and;
    }

    /**
     * Sets the and parameters into this {@code Query}
     *
     * @param andParam
     */
    public void setAnd(Map<String, String> andParam) {
        if (and == null) {
            and = new HashMap<String, String>();
        }

        for (Entry<String, String> entry : andParam.entrySet()) {
            String[] keyType = entry.getKey().split(":");
            if (keyType.length <= 1) {
                throw new IllegalArgumentException(
                    "You should provide the key parameter name with a colon and the data type, " +
                        "like myparam:number. Permitted types are:"
                        + PermittedParameterType.stringValues());
            }
            PermittedParameterType type = PermittedParameterType.valueOf(keyType[1]);
            if (type == null) {
                throw new IllegalArgumentException(
                    "You should provide the key parameter name with a colon and the data type, " +
                        "like myparam:number. Permitted types are:"
                        + PermittedParameterType.stringValues());
            }

            and.put(keyType[0], entry.getValue());
            registeredParameters.put(keyType[0], type);
        }
    }

    /**
     * Gets the select statement
     *
     * @return
     */
    public String getSelect() {
        return select;
    }

    /**
     * Sets the select statement
     *
     * @param select
     */
    public void setSelect(String select) {
        this.select = select;
    }

    /**
     * Gets the from statement
     *
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the from statement
     *
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the where statement
     *
     * @return
     */
    public String getWhere() {
        return where;
    }

    /**
     * Sets the where statement
     *
     * @param where
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * Gets the group by statement
     *
     * @return
     */
    public String getGroupBy() {
        return groupBy;
    }

    /**
     * Sets the group by statement
     *
     * @param groupBy
     */
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    /**
     * Gets the order by statement
     *
     * @return
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the order by statement
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * Gets the return type for this query result
     *
     * @return
     */
    public Class<T> getReturnType() {
        return returnType;
    }

    /**
     * Sets the return type for this query result
     *
     * @param returnType
     */
    public void setReturnType(Class<T> returnType) {
        this.returnType = returnType;
    }

    /**
     * Sets the return type for this query result
     *
     * @param type
     */
    public void setInto(Class<T> type) {
        this.returnType = type;
    }

    /**
     * Gets the return type for this query result
     *
     * @return
     */
    public Class<T> getInto() {
        return returnType;
    }

    /**
     * Gets the datasource
     *
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Sets the datasource
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the dynamic orders statements
     *
     * @return
     */
    public Map<String, String> getOrders() {
        return orders;
    }

    /**
     * Sets the dynamic orders statements
     *
     * @param multiOrderBy
     */
    public void setOrders(Map<String, String> multiOrderBy) {
        this.orders = multiOrderBy;
    }

    /**
     * Gets the query range builder
     *
     * @return
     */
    public SqlQueryRangeBuilder getRangeBuilder() {
        return rangeBuilder;
    }

    /**
     * Sets the query range builder
     *
     * @param rangeBuilder
     */
    public void setRangeBuilder(SqlQueryRangeBuilder rangeBuilder) {
        this.rangeBuilder = rangeBuilder;
    }

    /**
     * Gets the registered parameter types
     *
     * @return
     */
    Map<String, PermittedParameterType> getRegisteredParameters() {
        return registeredParameters;
    }

    /**
     * Sets the registered parameter types
     *
     * @return
     */
    Map<String, PermittedParameterType> getMandatoryParameters() {
        return mandatoryParameters;
    }

    /**
     * Enum with the permitted parameter types. These types are mostly created
     * for testing purposes. Whith the correct type mapped it's possible to
     * generate tests for all statements testing this query with all
     * possibilities.
     *
     * @author celio@delogic.com.br
     *
     */
    public enum PermittedParameterType {
        number(1),
        date(new Date()),
        text("text"),
        bool(Boolean.TRUE),
        numberslist(Arrays.asList(1, 2, 3)),
        textslist(Arrays.asList("text1", "text2"));

        private final Object example;

        private PermittedParameterType(Object ex) {
            this.example = ex;
        }

        public static String stringValues() {
            return Arrays.toString(values());
        }

        public static PermittedParameterType fromName(String type) {
            return PermittedParameterType.valueOf(type);
        }

        public Object getExample() {
            return example;
        }

    }

    /**
     * Initializes this query via Spring configuration when it's declared as an
     * Spring bean
     */
    public void afterPropertiesSet() throws Exception {
        maybeInitializeQuery();
    }

    public RepositoryData<T> getData() {
        return new RepositoryData<T>(count(), getList());
    }

    public RepositoryData<T> getData(Criteria criteria) {
        return new RepositoryData<T>(count(criteria), getList(criteria));
    }

}
