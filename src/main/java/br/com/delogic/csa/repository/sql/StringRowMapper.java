package br.com.delogic.csa.repository.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper created to return lists of String values
 *
 * @author celio@delogic.com.br
 *
 * @param <E>
 */
public class StringRowMapper<E> implements RowMapper<E> {

    @SuppressWarnings("unchecked")
    public E mapRow(ResultSet rs, int rowNum) throws SQLException {
        E e = (E) rs.getObject(1);
        if (e instanceof String) {
            return e;
        } else {
            throw new IllegalStateException("StringRowMapper is designed to return Strings from a one column only query");
        }
    }

}
