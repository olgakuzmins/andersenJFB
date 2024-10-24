package com.kuzmins.dao;

import com.kuzmins.config.ConnectionConfig;
import com.kuzmins.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class UserDAO {

    private final static Connection CONNECTION = ConnectionConfig.getConnection();
    private final static String INSERT_USER_QUERY = "INSERT INTO user_info (id, name, creation_date) VALUES (?, ?, ?)";
    private final static String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user_info WHERE id=?";
    private final static String DELETE_USER_BY_ID_QUERY = "DELETE FROM user_info where id=?";


    public void saveUser(User user) {
        try {
            PreparedStatement preparedStatement =
            CONNECTION.prepareStatement(INSERT_USER_QUERY);

            preparedStatement.setObject(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setTimestamp(3, Timestamp.from(user.getCreationDate()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User fetchUserById(UUID id) {
        User user = null;
        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(SELECT_USER_BY_ID_QUERY);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                UUID userId = UUID.fromString(resultSet.getString("id"));
                user.setId(userId);
                user.setName(resultSet.getString("name"));
                user.setCreationDate(resultSet.getTimestamp("creation_date").toInstant());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void deleteUserById(UUID userId) {
        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(DELETE_USER_BY_ID_QUERY);
            preparedStatement.setObject(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
