package br.com.delogic.csa.manager.repository.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper created to return Lists of Integer values.
 *
 * @author celio@delogic.com.br
 *
 * @param <E>
 */
public class IntegerRowMapper<E> implements RowMapper<E> {

    @SuppressWarnings("unchecked")
    public E mapRow(ResultSet rs, int rowNum) throws SQLException {
        E e = (E) Integer.valueOf(rs.getInt(1));
        if (e instanceof Integer) {
            return e;
        } else {
            throw new IllegalStateException("IntegerRowMapper is designed to return Integers from a one column only query");
        }
    }

}
