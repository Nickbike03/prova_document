package com.example.prove_documento.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao {
    private final DBManager dbManager;

    public DocumentDao() throws SQLException {
        this.dbManager = DBManager.getInstance();
    }

    public Document findById(Long id) throws SQLException {
        String sql = "SELECT id, doc FROM document WHERE id = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Document(
                        rs.getLong("id"),
                        rs.getBytes("doc")
                    );
                }
                return null;
            }
        }
    }

    public Document save(byte[] d) throws SQLException {
        String sql = "INSERT INTO document (doc) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(sql)) {
            stmt.setBytes(1, d);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Document(rs.getLong("id"), d);
                }
                throw new SQLException("Salvataggio fallito");
            }
        }
    }
}