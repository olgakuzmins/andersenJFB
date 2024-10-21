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
    private final static Connection connection = ConnectionConfig.getConnection();

    public void saveUser(User user) {
        try {
            PreparedStatement preparedStatement =
            connection.prepareStatement("INSERT INTO users (id, name, creation_date) VALUES (?, ?, ?)");

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
                    connection.prepareStatement("SELECT * FROM users WHERE id=?");
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
                    connection.prepareStatement("DELETE FROM users where id=?");
            preparedStatement.setObject(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
