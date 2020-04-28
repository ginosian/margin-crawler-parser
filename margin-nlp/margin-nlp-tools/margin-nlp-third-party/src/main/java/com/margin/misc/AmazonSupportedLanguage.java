package com.margin.misc;

public enum AmazonSupportedLanguage {
    en("ENGLISH"),
    es("SPANISH"),
    ar("ARABIC"),
    zh("CHINESE"),
    cs("CZECH"),
    fr("FRENCH"),
    de("GERMAN"),
    it("ITALIAN"),
    ja("JAPANESE"),
    pt("PORTUGUESE"),
    ru("RUSSIAN"),
    tr("TURKISH")
    ;

    private String meaning;

    AmazonSupportedLanguage(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }
}
