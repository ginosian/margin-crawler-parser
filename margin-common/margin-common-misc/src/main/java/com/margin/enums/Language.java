package com.margin.enums;
/*
 *   @author ironman
 *   @since  11/13/18
 */

import java.util.HashMap;
import java.util.Map;

public enum Language {

    UNKNOWN                 (""),
    INTERNATIONAL           ("en"),
    ENGLISH                 ("en"),
    ARABIC                  ("ar"),
    ARMENIAN                ("hy"),
    AZERBAIJANI             ("az"),
    BURMESE                 ("my"),
    BENGALI                 ("bn"),
    BULGARIAN               ("bg"),
    CHINESE                 ("zh"),
    CZECH                   ("cs"),
    GREEK                   ("el"),
    SPANISH                 ("es"),
    PERSIAN                 ("fa"),
    ICELANDIC               ("is"),
    DUTCH                   ("nl"),
    DANISH                  ("da"),
    SLOVAK                  ("sk"),
    SLOVENE                 ("sl"),
    FINNISH                 ("fi"),
    HINDI                   ("hi"),
    ITALIAN                 ("it"),
    INDONESIAN              ("id"),
    MACEDONIAN              ("mk"),
    PORTUGUESE              ("pt"),
    ESTONIAN                ("et"),
    LATVIAN                 ("lv"),
    LITHUANIAN              ("lt"),
    KOREAN                  ("ko"),
    ROMANIAN                ("ro"),
    GEORGIAN                ("ka"),
    RUSSIAN                 ("ru"),
    BELARUSIAN	            ("be"),
    KAZAKH	                ("kk"),
    TAJIK	                ("tg"),
    THAI	                ("th"),
    SERBIAN	                ("sr"),
    TURKISH	                ("tr"),
    UZBEKISH                ("uz"),
    TURKMEN                 ("tk"),
    KIRGHIZ                 ("ky"),
    HUNGARIAN               ("hu"),
    UKRAINIAN               ("uk"),
    JAPANESE                ("ja"),
    GERMAN                  ("de"),
    SWEDISH                 ("sv"),
    WELSH                   ("cy"),
    FRENCH                  ("fr"),
    POLISH                  ("pl"),
    NORWEGIAN               ("no");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private final static Map<String, Language> reverseMap = new HashMap<>();

    static {
        for (Language language : Language.values()) {
            reverseMap.put(language.getCode(), language);
        }
    }

    public static Language get(String languageCode) {
        if (!reverseMap.containsKey(languageCode)) {
            throw new RuntimeException("language code doesn't exist " + languageCode);
        }
        return reverseMap.get(languageCode);
    }

}
