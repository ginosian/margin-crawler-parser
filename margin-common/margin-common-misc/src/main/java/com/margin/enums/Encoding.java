package com.margin.enums;
/*
 *   @author ironman
 *   @since  11/21/18
 */

public enum Encoding {
    Default("UTF-8"),
    Cp1252("Cp1252"),
    Windows_1251("windows-1251"),
    UTF_8("UTF-8");

    private final String encoding;

    Encoding(String encoding) {
        this.encoding = encoding;
    }

    public String getAsString() {
        return encoding;
    }
}
