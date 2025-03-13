package com.jakub.bone.repository;

import com.jakub.bone.data.Datasource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExchangeRepository {

   private Datasource datasource;

    public ExchangeRepository(Datasource datasource) {
       this.datasource = new Datasource();
    }

    public void createTable() throws SQLException {
        try{
            String sql = "CREATE TABLE IF NOT EXISTS exchange (" +
                    "\"id\" SERIAL PRIMARY KEY, " +
                    "\"amount\" VARCHAR(255) NOT NULL, " +
                    "\"from\" VARCHAR(255) NOT NULL, " +
                    "\"to\" VARCHAR(255) NOT NULL" +
                    ")";
            Connection connection = datasource.connect();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void saveExchange(BigDecimal x, String y, String z) throws SQLException {
        String sql = "INSERT INTO exchange (\"amount\", \"from\", \"to\") VALUES (?, ?, ?)";
        try (Connection connection = datasource.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, x);
            stmt.setString(2, y);
            stmt.setString(3, z);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
