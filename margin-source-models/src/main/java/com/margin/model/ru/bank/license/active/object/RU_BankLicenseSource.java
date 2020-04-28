package com.margin.model.ru.bank.license.active.object;

import com.margin.model.AbstractSource;
import com.margin.model.ru.bank.license.RuBankLicenseSourceModel;
import com.margin.model.ru.bank.license.parser.RuBankLicenseParser;
import com.margin.model.ru.bank.license.scraper.RuBanksLicenseScraper;
import com.margin.parser.GenericParsingSource;
import com.margin.parser.Parser;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.scraper.GenericScrapingSource;
import com.margin.scraper.Scraper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RU_BankLicenseSource extends AbstractSource<
        GenericScrapingSource<String>,
        GenericScrapingSource<String>,
        GenericScrapedUnit<Document>,
        GenericParsingSource<Document>,
        RuBankLicenseSourceModel> {

    @Field("source")
    private RuBankLicenseSourceModel source;

    @Override
    public RuBankLicenseSourceModel getModel() {
        return source;
    }

    @Override
    public void setModel(RuBankLicenseSourceModel model) {
        this.source = model;
    }

    @Override
    public Parser<GenericParsingSource<Document>, RuBankLicenseSourceModel> getParser() {
        return new RuBankLicenseParser();
    }

    @Override
    public Scraper<GenericScrapingSource<String>, GenericScrapingSource<String>, GenericScrapedUnit<Document>> getScraper() {
        return new RuBanksLicenseScraper();
    }
}
