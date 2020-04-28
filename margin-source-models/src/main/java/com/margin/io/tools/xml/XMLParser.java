package com.margin.io.tools.xml;

import org.springframework.stereotype.Service;

@Service
public class XMLParser {

    public <T> T  unmarshal(String xmlString, T object) {
        return object;
    }
}
