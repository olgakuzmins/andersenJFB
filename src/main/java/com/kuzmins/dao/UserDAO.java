package com.kuzmins.dao;

import com.kuzmins.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String INSERT_USER_QUERY = "INSERT INTO user_info (id, name, creation_date) VALUES (?, ?, ?)";
    private final static String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user_info WHERE id=?";
    private final static String DELETE_USER_BY_ID_QUERY = "DELETE FROM user_info where id=?";


    public void saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getName(), Timestamp.from(user.getCreationDate()));
    }

    public User fetchUserById(UUID id) {
        return jdbcTemplate.query(SELECT_USER_BY_ID_QUERY, new UserMapper(), new Object[] {id})
                .stream().findAny().orElse(null);
    }

    public void deleteUserById(UUID userId) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, userId);
    }
}
