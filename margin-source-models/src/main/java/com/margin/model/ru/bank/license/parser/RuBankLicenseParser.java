package com.margin.model.ru.bank.license.parser;


import com.margin.model.ru.bank.license.RuBankLicenseSourceModel;
import com.margin.parser.GenericParsingSource;
import com.margin.parser.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

//TODO add data cleansing after cleansing service is ready
public class RuBankLicenseParser implements Parser<GenericParsingSource<Document>, RuBankLicenseSourceModel> {

    @Override
    public RuBankLicenseSourceModel parse(final GenericParsingSource<Document> source) {
        final Document document = source.getDocument();
        notNull(document, "Document can not be null.");
        final RuBankLicenseSourceModel target = new RuBankLicenseSourceModel();

        final Elements elements = document.getElementsByClass(" nodata");
        final Elements rows = elements.first().getElementsByTag("tr");

        final Iterator<Element> iterator = rows.iterator();

        if (iterator.hasNext()) {
            final List<String> fullName = iterator.next().getElementsByTag("td").eachText();
            if (fullName.size() > 1 && fullName.get(0).contains("Полное фирменное наименование")) {
                target.getUni().setFullCompanyName(fullName.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> abbreviatedName = iterator.next().getElementsByTag("td").eachText();
            if (abbreviatedName.size() > 1 && abbreviatedName.get(0).contains("Сокращённое фирменное наименование")) {
                target.setAbbreviatedCompanyName(abbreviatedName.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> registrationNumber = iterator.next().getElementsByTag("td").eachText();
            if (registrationNumber.size() > 1 && registrationNumber.get(0).contains("Регистрационный номер")) {
                target.getUni().setRegistrationNumber(registrationNumber.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> dateOfRegistration = iterator.next().getElementsByTag("td").eachText();
            if (dateOfRegistration.size() > 1 && dateOfRegistration.get(0).contains("Дата регистрации Банком России")) {
                target.setDateOfRegistration(dateOfRegistration.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> primaryStateRegistrationNumber = iterator.next().getElementsByTag("td").eachText();
            if (primaryStateRegistrationNumber.size() > 1 && primaryStateRegistrationNumber.get(0).contains("Основной государственный регистрационный номер")) {
                target.getUni().setPrimaryStateRegistrationNumber(primaryStateRegistrationNumber.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> bankIdentificationCode = iterator.next().getElementsByTag("td").eachText();
            if (bankIdentificationCode.size() > 1 && bankIdentificationCode.get(0).contains("БИК")) {
                target.getUni().setBankIdentificationCode(bankIdentificationCode.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> addressFromTheCharter = iterator.next().getElementsByTag("td").eachText();
            if (addressFromTheCharter.size() > 1 && addressFromTheCharter.get(0).contains("Адрес из устава")) {
                target.setAddressFromTheCharter(addressFromTheCharter.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> actualAddress = iterator.next().getElementsByTag("td").eachText();
            if (actualAddress.size() > 1 && actualAddress.get(0).contains("Адрес фактический")) {
                target.setActualAddress(actualAddress.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> phone = iterator.next().getElementsByTag("td").eachText();
            if (phone.size() > 1 && phone.get(0).contains("Телефон")) {
                target.setPhone(phone.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> charter = iterator.next().getElementsByTag("td").eachText();
            if (charter.size() > 1 && charter.get(0).contains("Устав")) {
                target.setCharter(charter.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> authorizedCapital = iterator.next().getElementsByTag("td").eachText();
            if (authorizedCapital.size() > 1 && authorizedCapital.get(0).contains("Уставный капитал")) {
                target.setAuthorizedCapital(authorizedCapital.get(1));
            }
        }

        if (iterator.hasNext()) {
            final Elements licenseAndDateRows = iterator.next().getElementsByTag("td");
            if (licenseAndDateRows.size() > 1 && licenseAndDateRows.get(0).text().contains("Лицензия (дата выдачи/последней замены)")) {
                try {
                    final Element rowValue = licenseAndDateRows.last();
                    final String link = rowValue.getElementsByTag("a").attr("href");
                    if (link != null) {
                        target.setLinkToLicenses(link);
                    }
                    final List<String> valueHtml = Arrays.asList(rowValue.html().split("<br>"));
                    target.setLicenseDetails(valueHtml.stream()
                            .filter(s -> !s.matches(".*href.*"))
                            .collect(Collectors.toList()));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        if (iterator.hasNext()) {
            final List<String> participationInTheDepositInsuranceSystem = iterator.next()
                    .getElementsByTag("td")
                    .eachText();
            if (participationInTheDepositInsuranceSystem.size() > 1 && participationInTheDepositInsuranceSystem.get(0).contains("Участие в системе страхования вкладов")) {
                target.setParticipationInTheDepositInsuranceSystem(participationInTheDepositInsuranceSystem.get(1));
            }
        }

        if (iterator.hasNext()) {
            final List<String> fullCompanyNameEn = iterator.next().getElementsByTag("td").eachText();
            if (fullCompanyNameEn.size() > 1 && fullCompanyNameEn.contains("Фирменное наименование на английском языке")) {
                target.setFullCompanyNameEn(fullCompanyNameEn.get(1));
            }
        }
        return target;
    }
}
