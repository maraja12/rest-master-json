package com.fon.rest_master.exception;

public class SchemaNotFoundException extends RuntimeException{

    public SchemaNotFoundException(String message) {
        super(message);
    }
}
