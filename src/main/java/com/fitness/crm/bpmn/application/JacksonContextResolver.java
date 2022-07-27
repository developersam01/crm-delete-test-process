package com.fitness.crm.bpmn.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    public JacksonContextResolver() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
    }

    public ObjectMapper getContext(final Class<?> aClass) {
        return objectMapper;
    }
}
