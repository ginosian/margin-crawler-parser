package com.margin.enums;

public enum Channel {
    JORDAN_REGISTRY("entity_jo_registry", false),
    RU_BANK_LICENSE("entity_ru_registry", true),
    SWISS_SANCTION("entity_int_sanction", false),
    US_OFAC_SANCTION("entity_int_sanction", false),
    US_CAATSA_SANCTION("entity_int_sanction", false),
    UNITED_KINGDOM_SANCTION("entity_int_sanction", false),
    UNITED_NATIONS_SANCTIONS("entity_int_sanction", false),
    UKRAINE_REGISTRY("entity_ua_registry", false);

    private final String databaseName;
    private final boolean isActive;

    Channel(String databaseName, boolean isActive) {
        this.databaseName = databaseName;
        this.isActive = isActive;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public boolean isActive() {
        return isActive;
    }
}
