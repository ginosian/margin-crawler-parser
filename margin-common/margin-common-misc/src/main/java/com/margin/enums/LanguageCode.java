package com.margin.enums;

import java.util.HashMap;
import java.util.Map;

public enum LanguageCode {

    UNKNOWN("none"),
    en ("ENGLISH"),
    ar ("ARABIC"),
    hy ("ARMENIAN"),
    az ("AZERBAIJANI"),
    my ("BURMESE"),
    bn ("BENGALI"),
    bg ("BULGARIAN"),
    zh ("CHINESE"),
    cs ("CZECH"),
    el ("GREEK"),
    es ("SPANISH"),
    fa ("PERSIAN"),
    is ("ICELANDIC"),
    nl ("DUTCH"),
    da ("DANISH"),
    sk ("SLOVAK"),
    sl ("SLOVENE"),
    fi ("FINNISH"),
    hi ("HINDI"),
    it ("ITALIAN"),
    id ("INDONESIAN"),
    mk ("MACEDONIAN"),
    pt ("PORTUGUESE"),
    et ("ESTONIAN"),
    lv ("LATVIAN"),
    lt ("LITHUANIAN"),
    ko ("KOREAN"),
    ro ("ROMANIAN"),
    ka ("GEORGIAN"),
    ru ("RUSSIAN"),
    be ("BELARUSIAN"),
    kk ("KAZAKH"),
    tg ("TAJIK"),
    th ("THAI"),
    sr ("SERBIAN"),
    tr ("TURKISH"),
    uz ("UZBEKISH"),
    tk ("TURKMEN"),
    ky ("KIRGHIZ"),
    hu ("HUNGARIAN"),
    uk ("UKRAINIAN"),
    ja ("JAPANESE"),
    de ("GERMAN"),
    sv ("SWEDISH"),
    cy ("WELSH"),
    fr ("FRENCH"),
    pl ("POLISH"),
    no ("NORWEGIAN");

    private final String languageName;

    LanguageCode(String code) {
        this.languageName = code;
    }

    public String getLanguageName() {
        return languageName;
    }

    private final static Map<String, Language> reverseMap = new HashMap<>();

    static {
        for (Language language : Language.values()) {
            reverseMap.put(language.getCode(), language);
        }
    }

    public static Language get(String languageCode) {
        if (!reverseMap.containsKey(languageCode)) {
            throw new RuntimeException("language languageName doesn't exist " + languageCode);
        }
        return reverseMap.get(languageCode);
    }
}
