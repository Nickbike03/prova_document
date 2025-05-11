package com.example.prove_documento.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static volatile DBManager instance;
    private final Connection connection;

    private DBManager() throws SQLException {
        this.connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/prova1",
            "postgres", 
            "1234"
        );
    }

    public static synchronized DBManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    // Aggiungi metodo per chiudere la connessione
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}