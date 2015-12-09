package com.uttesh.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Component
public class AbstractResource {

    protected static final String NULL_PARAM = "null";

    private ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
