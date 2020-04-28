package com.margin.config;

import com.margin.error.ApplicationExceptionMapper;
import com.margin.error.DtoValidationExceptionMapper;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.server.validation.ValidationFeature;

public class GenericJerseyConfig  extends ResourceConfig {

    public GenericJerseyConfig() {

        // Configurations
        register(MultiPartFeature.class);
        register(JacksonJsonProvider.class);
        register(ValidationFeature.class);
        register(ApplicationExceptionMapper.class);
        register(DtoValidationExceptionMapper.class);
        register(ObjectMapperResolver.class);

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, false);
        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", false);
        property("jersey.config.disableAutoDiscovery", true);

        EncodingFilter.enableFor(this, GZipEncoder.class);
    }
}
