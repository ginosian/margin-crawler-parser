package com.margin.model.ch.sanction.parser;

import com.margin.io.tools.xml.Entry;
import com.margin.io.tools.xml.XMLValidator;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class CHSanctionParser {

    public void parse(){
//        final MarginXMLParser parser = BeanUtil.getBean(MarginXMLParser.class);
//        final XMLMapEntryConverter converter = BeanUtil.getBean(XMLMapEntryConverter.class);

        try {

            final String xmlString = Jsoup.connect(
                    "https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml").get().toString()
                    .replace(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://tempuri.org/sdnList.xsd\"", "");

            final StringReader xml = new StringReader(xmlString);



            JAXBContext jc = JAXBContext.newInstance(
                    Entry.class);
//                    CHSanctionSourceEntity.class,
//                    CHSanctionAddress.class,
//                    CHSanctionAKA.class,
//                    CHSanctionId.class,
//                    CHSanctionPublishInformation.class);

            // Unmarshal Entry
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setEventHandler(new XMLValidator());

            Entry entry = (Entry) unmarshaller.unmarshal(xml);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            final Document document = builder.parse(is);
            System.out.println();

//            XStream magicApi = new XStream();
//            magicApi.registerConverter(converter);
//            magicApi.alias("sdnList", Map.class);
//
//            Map<String, String> extractedMap = (Map<String, String>) magicApi.fromXML(Jsoup.connect("https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml").get().toString());



//            XmlParserCreator parserCreator = new XmlParserCreator() {
//                @Override
//                public XmlPullParser createParser() {
//                    try {
//                        return XmlPullParserFactory.newInstance().newPullParser();
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            };
//
//            GsonXml gsonXml = new GsonXmlBuilder()
//                    .setXmlParserCreator(parserCreator)
//                    .create();
//
//            Entry model = gsonXml.fromXml(Jsoup.connect("https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml").get().toString(), Entry.class);




            // Unmarshal Course
//            Node contentNode = (Node) entry.getContent();
//            CHSanctionSourceEntity course = (CHSanctionSourceEntity) unmarshaller.unmarshal(contentNode);
//
//            Marshaller marshaller = jc.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(course, System.out);
//
//
//
//            JAXBContext jaxbContext = JAXBContext.newInstance(CHSanctionSourceEntity.class);
//            Jsoup.connect("https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml").get().toString();
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            CHSanctionSourceEntity entity = (CHSanctionSourceEntity) jaxbUnmarshaller.unmarshal(
//                    new URL("https://www.treasury.gov/ofac/downloads/consolidated/consolidated.xml")
//            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
