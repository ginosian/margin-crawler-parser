package com.margin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Helper {
    public Document getDocumentFromHtml() throws IOException, URISyntaxException {
        return Jsoup.parse(new File(this.getClass().getResource("/ru_bank_licence_data.html").toURI()), StandardCharsets.UTF_8.name());
    }
}
