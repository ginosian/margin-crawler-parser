package com.margin.model.ukraine.registry.active.object;

import com.margin.model.AbstractSource;
import com.margin.model.ukraine.registry.UA_RegistryModel;
import com.margin.model.ukraine.registry.parser.UA_RegistryParser;
import com.margin.model.ukraine.registry.scraper.UA_RegistryScraper;
import com.margin.parser.GenericParsingSource;
import com.margin.parser.Parser;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.scraper.GenericScrapingSource;
import com.margin.scraper.Scraper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UA_RegistrySource extends AbstractSource<
        GenericScrapingSource<String>,
        GenericScrapingSource<String>,
        GenericScrapedUnit<String>,
        GenericParsingSource<String>,
        UA_RegistryModel> {

    @Field("source")
    private UA_RegistryModel source;

    @Override
    public UA_RegistryModel getModel() {
        return source;
    }

    @Override
    public void setModel(UA_RegistryModel model) {
        this.source = model;
    }

    @Override
    public Parser<GenericParsingSource<String>, UA_RegistryModel> getParser() {
        return new UA_RegistryParser();
    }

    @Override
    public Scraper<GenericScrapingSource<String>, GenericScrapingSource<String>, GenericScrapedUnit<String>> getScraper() {
        return new UA_RegistryScraper();
    }
}
