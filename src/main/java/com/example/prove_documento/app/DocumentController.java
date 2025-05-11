package com.example.prove_documento.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File vuoto");
            }

            Document savedDocument = documentService.saveDocument(file.getBytes());
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                        "id", savedDocument.getId(),
                        "message", "Documento salvato con successo",
                        "size", file.getSize()
                    ));

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Errore di lettura file: " + e.getMessage()));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Errore database: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable Long id) {
        try {
            Document doc = documentService.getDocument(id);
            if (doc == null) {
                return ResponseEntity.notFound().build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // o OCTET_STREAM se tipo ignoto
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("document_" + id + ".pdf")
                    .build());

            return new ResponseEntity<>(doc.getDoc(), headers, HttpStatus.OK);

        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

