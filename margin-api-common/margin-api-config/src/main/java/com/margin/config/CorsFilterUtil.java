package com.margin.config;

import javax.ws.rs.container.ContainerResponseContext;

public abstract class CorsFilterUtil {

    public static void addAllowHeaders(ContainerResponseContext response) {

        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE");
    }
}