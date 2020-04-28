package com.margin.enums;

import java.util.HashMap;
import java.util.Map;

public enum Country {

    UNKNOWN							        ( "" , new Language[]{Language.UNKNOWN}),

    INTERNATIONAL   				        ("INT",new Language[]{Language.ENGLISH}),
    AFGHANISTAN						        ("AF", new Language[]{}),
    EGYPT							        ("EG", new Language[]{Language.ARABIC}),
    ALAND_ISLANDS					        ("AX", new Language[]{}),
    ALBANIA							        ("AL", new Language[]{}),
    ALGERIA							        ("DZ", new Language[]{Language.ARABIC, Language.FRENCH}),
    AMERICAN_SAMOA					        ("AS", new Language[]{}),
    ANDORRA							        ("AD", new Language[]{}),
    ANGOLA							        ("AO", new Language[]{}),
    ANGUILLA						        ("AI", new Language[]{}),
    ANTARCTICA						        ("AQ", new Language[]{}),
    ANTIGUA_AND_BARBUDA				        ("AG", new Language[]{}),
    ARGENTINA						        ("AR", new Language[]{Language.SPANISH}),
    ARMENIA							        ("AM", new Language[]{Language.ARMENIAN}),
    ARUBA							        ("AW", new Language[]{}),
    AZERBAIJAN						        ("AZ", new Language[]{Language.AZERBAIJANI}),
    AUSTRALIA						        ("AU", new Language[]{Language.ENGLISH}),
    BAHAMAS							        ("BS", new Language[]{Language.ENGLISH}),
    BAHRAIN							        ("BH", new Language[]{Language.ARABIC}),
    BANGLADESH						        ("BD", new Language[]{Language.ARABIC}),
    BARBADOS						        ("BB", new Language[]{Language.ENGLISH}),
    BELARUS							        ("BY", new Language[]{Language.BELARUSIAN}),
    BELGIUM							        ("BE", new Language[]{Language.DUTCH, Language.FRENCH, Language.GERMAN}),
    BELIZE							        ("BZ", new Language[]{Language.ENGLISH}),
    BENIN							        ("BJ", new Language[]{}),
    BERMUDA							        ("BM", new Language[]{Language.ENGLISH}),
    BHUTAN							        ("BT", new Language[]{}),
    BOLIVIA							        ("BO", new Language[]{}),
    BOSNIA_AND_HERZEGOVINA			        ("BA", new Language[]{}),
    BOTSWANA						        ("BW", new Language[]{}),
    BOUVET_ISLAND					        ("BV", new Language[]{}),
    BRAZIL							        ("BR", new Language[]{Language.PORTUGUESE}),
    BRITISH_INDIAN_OCEAN_TERRITORY		    ("IO", new Language[]{}),
    BRUNEI							        ("BN", new Language[]{}),
    BULGARIA						        ("BG", new Language[]{Language.BULGARIAN}),
    BURKINAFASO						        ("BF", new Language[]{}),
    BURUNDI							        ("BI", new Language[]{}),
    CHILE							        ("CL", new Language[]{Language.SPANISH}),
    CHINA							        ("CN", new Language[]{Language.CHINESE}),
    COOKISLANDS						        ("CK", new Language[]{}),
    COSTARICA						        ("CR", new Language[]{}),
    CURACAO							        ("CW", new Language[]{Language.DUTCH, Language.ENGLISH}),
    CONGO							        ("CD", new Language[]{}),
    DENMARK							        ("DK", new Language[]{Language.DANISH, Language.GERMAN}),
    DOMINICA						        ("DM", new Language[]{}),
    DOMINICAN_REPUBLIC				        ("DO", new Language[]{}),
    DJIBOUTI						        ("DJ", new Language[]{}),
    ETHIOPIA						        ("ET", new Language[]{}),
    EQUATORIAL_GUINEA				        ("GQ", new Language[]{}),
    ECUADOR							        ("EC", new Language[]{}),
    ELSALVADOR						        ("SV", new Language[]{}),
    ERITREA							        ("ER", new Language[]{}),
    ESTONIA							        ("EE", new Language[]{Language.ESTONIAN}),
    GERMANY							        ("DE", new Language[]{Language.GERMAN}),
    IVORYCOAST						        ("CI", new Language[]{Language.FRENCH}),
    FALKLAND_ISLANDS					    ("FK", new Language[]{}),
    FAROE_ISLANDS					        ("FO", new Language[]{Language.GERMAN, Language.DANISH}),
    FIJI							        ("FJ", new Language[]{}),
    FINLAND							        ("FI", new Language[]{Language.FINNISH}),
    FRANCE							        ("FR", new Language[]{Language.FRENCH}),
    FRENCH_GUIANA					        ("GF", new Language[]{}),
    FRENCH_POLYNESIA					    ("PF", new Language[]{}),
    FRENCH_SOUTH_AND_ANTARCTIC_LANDS	    ("TF", new Language[]{}),
    GABON							        ("GA", new Language[]{}),
    GAMBIA							        ("GM", new Language[]{}),
    GEORGIA							        ("GE", new Language[]{}),
    GHANA							        ("GH", new Language[]{Language.ENGLISH}),
    GIBRALTAR						        ("GI", new Language[]{Language.ENGLISH}),
    GRENADA							        ("GD", new Language[]{}),
    GREECE							        ("GR", new Language[]{Language.GREEK}),
    GREENLAND						        ("GL", new Language[]{}),
    GUADELOUPE						        ("GP", new Language[]{}),
    GUAM							        ("GU", new Language[]{}),
    GUATEMALA						        ("GT", new Language[]{Language.SPANISH}),
    GUERNSEY						        ("GG", new Language[]{Language.ENGLISH}),
    GUINEA							        ("GN", new Language[]{}),
    GUINEABISSAU					        ("GW", new Language[]{}),
    GUYANA							        ("GY", new Language[]{Language.ENGLISH}),
    HAITI							        ("HT", new Language[]{}),
    HEARD_ISLAND_AND_MCDONALD_ISLANDS	    ("HM", new Language[]{}),
    HONDURAS						        ("HN", new Language[]{}),
    HONGKONG						        ("HK", new Language[]{Language.ENGLISH, Language.CHINESE}),
    INDIA							        ("IN", new Language[]{Language.ENGLISH, Language.HINDI}),
    INDONESIA						        ("ID", new Language[]{}),
    ISLEOFMAN						        ("IM", new Language[]{Language.ENGLISH}),
    IRAQ							        ("IQ", new Language[]{Language.ARABIC}),
    IRAN							        ("IR", new Language[]{Language.ARABIC}),
    IRELAND							        ("IE", new Language[]{Language.ENGLISH}),
    ICELAND							        ("IS", new Language[]{Language.ICELANDIC}),
    ISRAEL							        ("IL", new Language[]{}),
    ITALY							        ("IT", new Language[]{Language.ITALIAN}),
    JAMAICA							        ("JM", new Language[]{Language.ENGLISH}),
    JAPAN							        ("JP", new Language[]{Language.JAPANESE}),
    YEMEN							        ("YE", new Language[]{}),
    JERSEY							        ("JE", new Language[]{Language.ENGLISH}),
    JORDAN							        ("JO", new Language[]{Language.ARABIC}),
    BRITISH_VIRGIN_ISLANDS			        ("VG", new Language[]{Language.ENGLISH}),
    VIRGIN_ISLANDS					        ("VI", new Language[]{Language.ENGLISH}),
    CAYMAN_ISLANDS					        ("KY", new Language[]{Language.ENGLISH}),
    CAMBODIA						        ("KH", new Language[]{}),
    CAMEROON						        ("CM", new Language[]{}),
    CANADA							        ("CA", new Language[]{Language.ENGLISH, Language.FRENCH}),
    CAPEVERDE						        ("CV", new Language[]{}),
    KAZAKHSTAN						        ("KZ", new Language[]{}),
    QATAR							        ("QA", new Language[]{Language.ARABIC}),
    KENYA							        ("KE", new Language[]{Language.ENGLISH}),
    KYRGYZSTAN						        ("KG", new Language[]{Language.KIRGHIZ}),
    KIRIBATI						        ("KI", new Language[]{}),
    COCOS_ISLANDS					        ("CC", new Language[]{}),
    COLOMBIA						        ("CO", new Language[]{}),
    COMOROS							        ("KM", new Language[]{}),
    REPUBLIC_OF_THE_CONGO				    ("CG", new Language[]{}),
    KOSOVO							        ("XK", new Language[]{}),
    CROATIA							        ("HR", new Language[]{}),
    CUBA							        ("CU", new Language[]{Language.SPANISH}),
    KUWAIT							        ("KW", new Language[]{Language.ARABIC}),
    LAOS							        ("LA", new Language[]{}),
    LESOTHO							        ("LS", new Language[]{}),
    LATVIA							        ("LV", new Language[]{Language.LATVIAN}),
    LEBANON							        ("LB", new Language[]{Language.ARABIC, Language.FRENCH}),
    LIBERIA							        ("LR", new Language[]{}),
    LIBYA							        ("LY", new Language[]{}),
    LIECHTENSTEIN					        ("LI", new Language[]{Language.GERMAN}),
    LITHUANIA						        ("LT", new Language[]{Language.LITHUANIAN}),
    LUXEMBOURG						        ("LU", new Language[]{Language.FRENCH}),
    MACAU							        ("MO", new Language[]{}),
    MADAGASCAR						        ("MG", new Language[]{}),
    MALAWI							        ("MW", new Language[]{}),
    MALAYSIA						        ("MY", new Language[]{Language.ENGLISH}),
    MALDIVES						        ("MV", new Language[]{}),
    MALI							        ("ML", new Language[]{}),
    MALTA							        ("MT", new Language[]{Language.ENGLISH}),
    MOROCCO							        ("MA", new Language[]{}),
    MARSHALL_ISLANDS					    ("MH", new Language[]{}),
    MARTINIQUE						        ("MQ", new Language[]{}),
    MAURITANIA						        ("MR", new Language[]{}),
    MAURITIUS						        ("MU", new Language[]{Language.FRENCH}),
    MAYOTTE							        ("YT", new Language[]{}),
    MACEDONIA						        ("MK", new Language[]{Language.MACEDONIAN}),
    MEXICO							        ("MX", new Language[]{Language.SPANISH}),
    MICRONESIE						        ("FM", new Language[]{}),
    MOLDOVA							        ("MD", new Language[]{Language.ROMANIAN}),
    MONACO							        ("MC", new Language[]{Language.FRENCH}),
    MONGOLIA						        ("MN", new Language[]{}),
    MONTENEGRO						        ("ME", new Language[]{}),
    MONTSERRAT						        ("MS", new Language[]{}),
    MOZAMBIQUE						        ("MZ", new Language[]{Language.PORTUGUESE}),
    MYANMAR							        ("MM", new Language[]{Language.BURMESE}),
    NAMIBIA							        ("NA", new Language[]{}),
    NAURUMY							        ("NR", new Language[]{}),
    NEPAL							        ("NP", new Language[]{}),
    NEW_CALEDONIA					        ("NC", new Language[]{}),
    NEW_ZEALAND						        ("NZ", new Language[]{Language.ENGLISH}),
    NICARAGUA						        ("NI", new Language[]{Language.SPANISH, Language.ENGLISH}),
    NETHERLANDS						        ("NL", new Language[]{Language.DUTCH}),
    NETHERLANDS_ANTILLES				    ("AN", new Language[]{}),
    NIGER							        ("NE", new Language[]{}),
    NIGERIA							        ("NG", new Language[]{Language.ENGLISH}),
    NIUE							        ("NU", new Language[]{}),
    NORTH_KOREA						        ("KP", new Language[]{}),
    NORTH_MARIANA_ISLANDS				    ("MP", new Language[]{}),
    NORFOLK_ISLAND					        ("NF", new Language[]{}),
    NORWAY							        ("NO", new Language[]{Language.NORWEGIAN}),
    OMAN							        ("OM", new Language[]{}),
    AUSTRIA							        ("AT", new Language[]{Language.GERMAN}),
    TIMORLESTE						        ("TL", new Language[]{}),
    PAKISTAN						        ("PK", new Language[]{Language.ARABIC}),
    PALESTINE						        ("PS", new Language[]{}),
    PALAU							        ("PW", new Language[]{}),
    PANAMA							        ("PA", new Language[]{}),
    PAPUANEWGUINEA					        ("PG", new Language[]{}),
    PARAGUAY						        ("PY", new Language[]{}),
    PERU							        ("PE", new Language[]{}),
    PHILIPPINES						        ("PH", new Language[]{}),
    PITCAIRN_ISLANDS					    ("PN", new Language[]{}),
    POLAND							        ("PL", new Language[]{Language.POLISH}),
    PORTUGAL						        ("PT", new Language[]{Language.PORTUGUESE}),
    PUERTORICO						        ("PR", new Language[]{}),
    REUNION							        ("RE", new Language[]{}),
    RWANDA							        ("RW", new Language[]{}),
    ROMANIA							        ("RO", new Language[]{Language.ROMANIAN}),
    RUSSIA							        ("RU", new Language[]{Language.RUSSIAN}),
    SOLOMON_ISLANDS					        ("SB", new Language[]{}),
    ZAMBIA							        ("ZM", new Language[]{Language.ENGLISH}),
    SAMOA							        ("WS", new Language[]{}),
    SANMARINO						        ("SM", new Language[]{}),
    SAINTBARTHELEMY					        ("BL", new Language[]{}),
    SAOTOME_AND_PRINCIPE			        ("ST", new Language[]{Language.PORTUGUESE}),
    SAUDIARABIA						        ("SA", new Language[]{Language.ARABIC}),
    SWEDEN							        ("SE", new Language[]{Language.SWEDISH}),
    SWITZERLAND						        ("CH", new Language[]{Language.FRENCH, Language.GERMAN}),
    SENEGAL							        ("SN", new Language[]{}),
    SERBIA							        ("RS", new Language[]{Language.SERBIAN}),
    SEYCHELLES						        ("SC", new Language[]{}),
    SIERRALEONE						        ("SL", new Language[]{}),
    ZIMBABWE						        ("ZW", new Language[]{Language.ENGLISH}),
    SINGAPORE						        ("SG", new Language[]{Language.ENGLISH}),
    SAINTMARTIN						        ("SX", new Language[]{}),
    SLOVAKIA						        ("SK", new Language[]{Language.SLOVAK}),
    SLOVENIA						        ("SI", new Language[]{Language.SLOVENE}),
    SOMALIA							        ("SO", new Language[]{}),
    SPAIN							        ("ES", new Language[]{Language.SPANISH}),
    SRILANKA						        ("LK", new Language[]{Language.ENGLISH}),
    SAINT_HELENA_ASCENSION_TRISTAN_CUNHA    ("SH", new Language[]{}),
    SAINT_KITTS_AND_NEVIS				    ("KN", new Language[]{}),
    SAINT_LUCIA						        ("LC", new Language[]{}),
    SAINT_PIERRE_AND_MIQUELON			    ("PM", new Language[]{}),
    SAINT_VINCENT_AND_THEGRENADINES	        ("VC", new Language[]{}),
    SOUTH_AFRICA						    ("ZA", new Language[]{Language.ENGLISH, Language.FRENCH}),
    SUDAN							        ("SD", new Language[]{}),
    SOUTH_GEORGIA_SOUTH_SANDWICH_ISLANDS    ("GS", new Language[]{}),
    SOUTHKOREA						        ("KR", new Language[]{}),
    SOUTHSUDAN						        ("SS", new Language[]{}),
    SURINAME						        ("SR", new Language[]{}),
    SVALBARD						        ("SJ", new Language[]{}),
    SWAZILAND						        ("SZ", new Language[]{}),
    SYRIA							        ("SY", new Language[]{}),
    TAJIKISTAN						        ("TJ", new Language[]{Language.TAJIK}),
    TAIWAN							        ("TW", new Language[]{Language.CHINESE}),
    TANZANIA						        ("TZ", new Language[]{}),
    THAILAND						        ("TH", new Language[]{Language.THAI}),
    TOGO							        ("TG", new Language[]{}),
    TOKELAU							        ("TK", new Language[]{}),
    TONGA							        ("TO", new Language[]{}),
    TRINIDADANDTOBAGO				        ("TT", new Language[]{Language.ENGLISH}),
    CHAD							        ("TD", new Language[]{Language.ARABIC, Language.FRENCH}),
    CZECHREPUBLIC					        ("CZ", new Language[]{Language.CZECH}),
    TUNISIA							        ("TN", new Language[]{Language.ARABIC}),
    TURKEY							        ("TR", new Language[]{Language.TURKISH}),
    TURKMENISTAN					        ("TM", new Language[]{Language.TURKMEN}),
    TURKS_AND_CAICOS_ISLANDS			    ("TC", new Language[]{}),
    TUVALU							        ("TV", new Language[]{Language.ENGLISH}),
    UGANDA							        ("UG", new Language[]{}),
    UKRAINE							        ("UA", new Language[]{Language.UKRAINIAN}),
    HUNGARY							        ("HU", new Language[]{}),
    URUGUAY							        ("UY", new Language[]{Language.SPANISH}),
    UZBEKISTAN						        ("UZ", new Language[]{Language.UZBEKISH}),
    VANUATU							        ("VU", new Language[]{}),
    VATICAN_CITY						    ("VA", new Language[]{}),
    VENEZUELA						        ("VE", new Language[]{Language.SPANISH}),
    UNITED_ARAB_EMIRATES				    ("AE", new Language[]{Language.ARABIC}),
    UNITED_STATES					        ("US", new Language[]{Language.ENGLISH}),
    UNITED_KINGDOM					        ("UK", new Language[]{Language.ENGLISH}),
    VIETNAM							        ("VN", new Language[]{}),
    LISA_AND_FUTUNA					        ("WF", new Language[]{}),
    CHRISTMAS_ISLAND					    ("CX", new Language[]{}),
    WEST_SAHARA						        ("EH", new Language[]{}),
    CENTRAL_AFRICAN_REPUBLIC			    ("CF", new Language[]{}),
    CYPRUS							        ("CY", new Language[]{Language.GREEK}),
    CARIBBEAN_NETHERLANDS			        ("BQ", new Language[]{}),
    SAINT_MARTIN_FRANCE				        ("MF", new Language[]{}),
    UNITED_STATES_MINOROUTLYING_ISLANDS     ("UM", new Language[]{}),

    GREATBRITAIN					        ("GB", new Language[]{});

    private final String ISO_3166_code;
    private final Language[] languages;
    private final static Map<String, Country> reverseMap = new HashMap<>();

    Country(String ISO_3166_code, Language[] language) {
        this.ISO_3166_code = ISO_3166_code;
        this.languages = language;
    }

    public String getISO_3166_code() {
        return ISO_3166_code;
    }

    public String getLanguage() {
        return languages.length > 0 ? languages[0].getCode() : "";
    }

    public Language[] getLanguages() {
        return languages;
    }

    static {
        for (Country country : Country.values()) {
            reverseMap.put(country.ISO_3166_code, country);
        }
    }

    public static Country get(String countryCode) {
        if (!reverseMap.containsKey(countryCode)) {
            throw new RuntimeException("countryEntity code doesn't exist " + countryCode);
        }
        return reverseMap.get(countryCode);
    }
}
