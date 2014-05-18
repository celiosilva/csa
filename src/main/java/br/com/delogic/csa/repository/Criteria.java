package br.com.delogic.csa.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delogic.jfunk.Has;

/**
 * Parameters which can be used with a {@code QueryRepository} get methods,
 * filtering the query via statements to be appended.
 *
 * @author celio@delogic.com.br
 *
 */
public class Criteria {

    /**
     * OrderBy keys to activate order previously written.
     */
    private String[]            parameterizedOrderBy;

    /**
     * OrderBy statements to be appended to the query.
     */
    private String[]            orderBy;

    /**
     * Start row for the results
     */
    private Long                offset;

    /**
     * End row for the results
     */
    private Long                limit;

    /**
     * Parameters sent to the database
     */
    private Map<String, Object> parameters;

    private static final Logger logger = LoggerFactory.getLogger(Criteria.class);

    /**
     * Will add the parameter with its key to the query only if the value is not
     * null
     *
     * @param key
     * @param value
     */
    public Criteria(String key, Object value) {
        addParameter(key, value);
    }

    /**
     * Will add the parameter with its key to the query only if the value is not
     * null
     *
     * @param key
     * @param values
     */
    public Criteria(String key, Object... values) {
        addParameter(key, values);
    }

    /**
     * Creates an empty query parameter
     */
    public Criteria() {}

    /**
     * Gets the order by keys
     *
     * @return
     */
    public String[] getParameterizedOrderBy() {
        return parameterizedOrderBy;
    }

    /**
     * Sets the order by keys
     *
     * @param orderByKey
     */
    public void setParameterizedOrderBy(String... orderByKey) {
        this.parameterizedOrderBy = orderByKey;
    }

    /**
     * Gets the parameters
     *
     * @return
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters
     *
     * @param parameterizedOrderBy
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * Will add the parameter with its key to the query only if the value is not
     * null.
     *
     * @param key
     * @param value
     * @return
     */
    public Criteria addParameter(String key, Object value) {
        if (!Has.content(value)) {
            if (logger.isInfoEnabled()) {
                logger.info("Could not add empty value for key:" + key);
            }
            return this;
        }
        if (parameters == null) {
            parameters = new HashMap<String, Object>();
        }
        parameters.put(key, value);
        return this;
    }

    /**
     * Will add the String representation of the Enum via {@code .name()} method
     * as a parameter, with its key to the query, only if the value is not null.
     *
     * @param key
     * @param value
     * @return
     */
    public Criteria addParameter(String key, Enum<?> value) {
        if (value == null) {
            if (logger.isInfoEnabled()) {
                logger.info("Could not add null value for key:" + key);
            }
            return this;
        }
        return addParameter(key, value.name());
    }

    /**
     * Will add the parameter with its key to the query only if the value is not
     * null. This will set an array of values to the query with the same key,
     * not a bunch of key/values at once.
     *
     * @param key
     * @param values
     * @return
     */
    public Criteria addParameter(String key, Object... values) {
        if (values == null || values.length == 0) {
            if (logger.isInfoEnabled()) {
                logger.info("Could not add empty values for key:" + key);
            }
            return this;
        }
        return addParameter(key, Arrays.asList(values));
    }

    /**
     * This is used to activate and "and" statement which doesn't have
     * parameters, only appends another sql statements.
     *
     * @param key
     * @return
     */
    public Criteria activateParameter(String key) {
        return addParameter(key, key);
    }

    /**
     * Gets the start row for the data to be returned.
     *
     * @return
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Sets the start row for the data to be returned.
     *
     * @param offset
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * Gets the amount of rows to be returned.
     *
     * @return
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * Sets the amount of rows to be returned.
     *
     * @param limit
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * Gets the orderby statements to be appended to the query.
     *
     * @return
     */
    public String[] getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the order statements to be appended to the query.
     *
     * @param orderBy
     */
    public void setOrderBy(String... orderBy) {
        this.orderBy = orderBy;
    }

}
