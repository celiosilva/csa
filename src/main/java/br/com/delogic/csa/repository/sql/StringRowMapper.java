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
        return (E) rs.getString(1);
    }

}
