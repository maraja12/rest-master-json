package com.fon.rest_master.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fon.rest_master.exception.JsonValidationSchemaException;
import com.fon.rest_master.exception.SchemaNotFoundException;

public interface ValidateInvoiceService {

    boolean validateInvoicesDocument(String document) throws SchemaNotFoundException,
            JsonValidationSchemaException;
}
