package br.com.delogic.csa.manager.repository.sql;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parameters which can be sent into the query's {@code getList} method,
 * filtering the query via dynamic and statements append.
 *
 * @author celio@delogic.com.br
 *
 */
public class Criteria {

    /**
     * Order by to be executed
     */
    private String[]            orderByKey;

    /**
     * Start row for the results
     */
    private Long                startRow;

    /**
     * End row for the results
     */
    private Long                endRow;

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
    public String[] getOrderByKey() {
        return orderByKey;
    }

    /**
     * Sets the order by keys
     *
     * @param orderByKey
     */
    public void setOrderByKey(String... orderByKey) {
        this.orderByKey = orderByKey;
    }

    /**
     * Gets the start row
     *
     * @return
     */
    public Long getStartRow() {
        return startRow;
    }

    /**
     * Gets the end row
     *
     * @return
     */
    public Long getEndRow() {
        return endRow;
    }

    /**
     * Sets the start row
     *
     * @param orderByKey
     */
    public void setStartRow(Long startRow) {
        this.startRow = startRow;
    }

    /**
     * Sets the end row
     *
     * @param orderByKey
     */
    public void setEndRow(Long endRow) {
        this.endRow = endRow;
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
     * @param orderByKey
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
        if (value == null) {
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
     * parameter.
     *
     * @param key
     * @return
     */
    public Criteria activateParameter(String key) {
        return addParameter(key, "");
    }

}
