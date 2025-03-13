package com.jakub.bone.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
    private final int PORT = 5432;
    private final String USERNAME = "jakub";
    private final String PASSWORD = "jakub123";
    private final String DATABASE = "exchange_db";
    private final String url = String.format("jdbc:postgresql://localhost:%d/%s", PORT, DATABASE);
    private Connection connection;

    public Connection connect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            this.connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() throws SQLException{
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
