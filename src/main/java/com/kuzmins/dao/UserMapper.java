package com.kuzmins.dao;

import com.kuzmins.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        UUID userId = UUID.fromString(resultSet.getString("id"));
        user.setId(userId);
        user.setName(resultSet.getString("name"));
        user.setCreationDate(resultSet.getTimestamp("creation_date").toInstant());
        return user;
    }
}
