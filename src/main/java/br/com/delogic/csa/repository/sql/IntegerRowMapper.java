package br.com.delogic.csa.repository.sql;

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
        return (E) Integer.valueOf(rs.getInt(1));
    }

}
