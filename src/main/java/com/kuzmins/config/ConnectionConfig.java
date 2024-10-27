package com.kuzmins.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    private static final Connection connection;

    static {
        try {
            setProperties();
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setProperties() {

        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {

            Properties prop = new Properties();
            prop.load(input);

            URL=prop.getProperty("url");
            DRIVER=prop.getProperty("driver");
            USERNAME=prop.getProperty("username_value");
            PASSWORD=prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
