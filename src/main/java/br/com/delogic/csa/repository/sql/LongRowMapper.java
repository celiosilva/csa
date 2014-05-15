package br.com.delogic.csa.repository.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper created to return lists of Long values.
 *
 * @author celio@delogic.com.br
 *
 * @param <E>
 */
public class LongRowMapper<E> implements RowMapper<E> {

    @SuppressWarnings("unchecked")
    public E mapRow(ResultSet rs, int rowNum) throws SQLException {
        return (E) Long.valueOf(rs.getLong(1));
    }

}