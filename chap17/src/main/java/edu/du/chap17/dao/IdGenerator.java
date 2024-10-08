package edu.du.chap17.dao;

import edu.du.chap17.service.IdGenerationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IdGenerator {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int generateNextId(String sequenceName)
            throws IdGenerationFailedException {

            int id = jdbcTemplate.queryForObject("select next_value from id_sequence where sequence_name = ? for update", Integer.class, sequenceName);

            id++;

            jdbcTemplate.update("update id_sequence set next_value = ? where sequence_name = ?", id, sequenceName);

            return id;

    }
}
