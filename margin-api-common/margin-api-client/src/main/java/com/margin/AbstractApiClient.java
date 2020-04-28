package com.margin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.message.GZipEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.Closeable;
import java.io.IOException;

@Getter
public abstract class AbstractApiClient implements Closeable {

    private final ObjectMapper mapper;
    private final WebTarget webTarget;
    private final Client client;

    public AbstractApiClient(final String apiUrl) {
        this.mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule("apiErrorMapper", Version.unknownVersion());
//        mapper.registerModule(module);
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ClientConfig cc = new ClientConfig();
        cc.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        cc.register(GZipEncoder.class);
        cc.register(EncodingFilter.class);
//        cc.register(JacksonFeature.class);
        cc.register(new JacksonJsonProvider(mapper));

        this.client = ClientBuilder.newClient(cc);
        this.webTarget = client.target(apiUrl);
    }


    @Override
    public void close() throws IOException {

    }
}
