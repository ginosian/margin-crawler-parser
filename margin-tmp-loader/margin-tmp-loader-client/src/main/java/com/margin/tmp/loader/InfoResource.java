package com.margin.tmp.loader;

import com.margin.AbstractApiResource;
import com.margin.dto.info.InfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class InfoResource extends AbstractApiResource {
    private static String PATH_SUFFIX = "/info";

    private final Logger logger = LoggerFactory.getLogger(InfoResource.class);

    public InfoResource(Client client, WebTarget rootTarget) {
        super(client, rootTarget, PATH_SUFFIX);
    }

    public InfoDTO info() {
        return doGet("", InfoDTO.class);
    }
}
