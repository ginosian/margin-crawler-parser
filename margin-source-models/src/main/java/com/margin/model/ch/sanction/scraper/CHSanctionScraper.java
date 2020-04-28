package com.margin.model.ch.sanction.scraper;

import com.margin.model.ch.sanction.entity.CHSanctionSourceEntity;
import org.jsoup.Jsoup;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

public class CHSanctionScraper {

    public void scrap(){
        try {
            Jsoup.connect("https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml").get().toString();
            JAXBContext jaxbContext = JAXBContext.newInstance(CHSanctionSourceEntity.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CHSanctionSourceEntity entity = (CHSanctionSourceEntity) jaxbUnmarshaller.unmarshal(
                    new URL("https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
