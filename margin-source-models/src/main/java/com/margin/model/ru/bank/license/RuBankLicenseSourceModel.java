package com.margin.model.ru.bank.license;

import com.margin.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ru_registry_source")
@Getter
@Setter
public class RuBankLicenseSourceModel extends AbstractModel {
    private RuBankLicenseSourceUniModel uni = new RuBankLicenseSourceUniModel();
    private String fullCompanyNameEn;
    private String abbreviatedCompanyName;
    private String dateOfRegistration;
    private String addressFromTheCharter;
    private String actualAddress;
    private String phone;
    private String charter;
    private String authorizedCapital;
    private List<String> licenseDetails;
    private String linkToLicenses;
    private String participationInTheDepositInsuranceSystem;
}
