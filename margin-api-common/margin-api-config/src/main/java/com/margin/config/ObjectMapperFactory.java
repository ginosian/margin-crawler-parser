package com.margin.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Primary
    @Bean
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Primary
    @Bean
    public ObjectWriter getObjectWriter() {
        return objectMapper.writer();
    }

    @Primary
    @Bean
    public ObjectReader getObjectReader() {
        return objectMapper.reader();
    }

    @Primary
    @Bean
    public JsonFactory getJsonFactory() {
        return objectMapper.getFactory();
    }

    public static ObjectMapper getGlobalObjectMapper() {
        return objectMapper;
    }
}
