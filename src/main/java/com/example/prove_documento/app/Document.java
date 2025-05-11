package com.example.prove_documento.app;

public class Document {
    private Long id;
    private byte[] doc;

    public Document(Long id, byte[] doc) {
        this.id = id;
        this.doc = doc;
    }

    public Document(byte[] doc) {
        this.doc = doc;
    }

    public void setId(Long _id){
        this.id = _id;
    }

    public void setDoc(byte[] _doc){
        this.doc = _doc;
    }

    public Long getId() {
        return id;
    }

    public byte[] getDoc() {
        return doc;
    }
}

