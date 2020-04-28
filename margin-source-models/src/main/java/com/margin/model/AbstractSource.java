package com.margin.model;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.converter.Converter;
import com.margin.parser.GenericParsingSource;
import com.margin.parser.Parser;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.scraper.GenericScrapingSource;
import com.margin.scraper.Scraper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AbstractSource<
        ScrapingSource extends GenericScrapingSource,
        ScrapingPageSource extends GenericScrapingSource,
        ScrapingResult extends GenericScrapedUnit,
        ParsingSource extends GenericParsingSource,
        Model extends AbstractModel> implements ModelHolder<Model>, Serializable {
    @Id
    private String _id;

    @Field
    private Country country;

    @Field
    private Channel channel;

    @Field
    private Integer version;

    @Field
    private String sourcePath;

    @Transient
    private Scraper<ScrapingSource, ScrapingPageSource, ScrapingResult> scraper;

    @Transient
    private Parser<ParsingSource, Model> parser;


    public AbstractSource(Country country, Channel channel, Integer version, String sourcePath) {
        this.country = country;
        this.channel = channel;
        this.version = version;
        this.sourcePath = sourcePath;
    }
}
