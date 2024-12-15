package com.fon.rest_master.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fon.rest_master.exception.JsonParsingException;
import com.fon.rest_master.exception.JsonValidationSchemaException;
import com.fon.rest_master.exception.SchemaNotFoundException;
import com.fon.rest_master.service.ValidateInvoiceService;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Set;

@Service
public class ValidateInvoiceServiceImpl implements ValidateInvoiceService {

    private ObjectMapper objectMapper;

    public ValidateInvoiceServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean validateInvoicesDocument(String document) throws SchemaNotFoundException,
            JsonValidationSchemaException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("invoices-schema.json");
        if (stream == null) {
            throw new SchemaNotFoundException("Invoices JSON schema not found.");
        }
        JsonSchema schema = JsonSchemaFactory.getInstance().getSchema(stream);
        //parsing json input
        //readTree could throw JsonProcessingException if document doesn't have JSON structure
        try{
            JsonNode jsonNode = objectMapper.readTree(document);
            //validate json input against the schema
            Set<ValidationMessage> errors = schema.validate(jsonNode);
            if(!errors.isEmpty()) {
                throw new JsonValidationSchemaException("Json validation against schema failed. Check your values");
            }
        }
        catch (JsonProcessingException ex){
            throw new JsonParsingException(ex.getMessage());
        }
        return true;
    }
}
