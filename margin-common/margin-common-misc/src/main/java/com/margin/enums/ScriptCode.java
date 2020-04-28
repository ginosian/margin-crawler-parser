package com.margin.enums;

/**
 * Refer to the http://unicode.org/iso15924/iso15924-codes.html for the whole list.
 */
public enum ScriptCode {
    LATIN("LATIN"),
    CYRL("CYRILLIC"),
    JPAN("JAPANESE");

    private String meaning;

    ScriptCode(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }
}
