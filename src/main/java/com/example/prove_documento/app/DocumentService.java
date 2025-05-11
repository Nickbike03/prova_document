package com.example.prove_documento.app;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    public Document getDocument(Long id) throws SQLException {
        return documentDao.findById(id);
    }

     public Document saveDocument(byte[] documentContent) throws SQLException {
        if (documentContent == null || documentContent.length == 0) {
            throw new IllegalArgumentException("Il documento non può essere vuoto");
        }
        return documentDao.save(documentContent);
    }
}

