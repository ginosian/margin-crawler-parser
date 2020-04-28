package com.margin;

import com.margin.model.ru.bank.license.parser.RuBankLicenseParser;
import com.margin.parser.GenericParsingSource;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(MockitoJUnitRunner.class)
public class RuBankLicenseParserTest {

    private Helper helper = new Helper();
    private RuBankLicenseParser testingInstance = new RuBankLicenseParser();

    @Test
    public void parse() throws IOException, URISyntaxException {
        Document document = helper.getDocumentFromHtml();
        testingInstance.parse(new GenericParsingSource<>(document));
    }
}