package com.margin.service;
/*
 *   @author ironman
 *   @since  11/16/18
 */

public final class ValidationTools {
    private static final String NULL_MSG = "Could not find %s:'%s'";
    public static final String CANNOT_BE_NULL = " cannot be null";

    public static void entityExists(Object object, String key, String value) {
        if(object == null){
            throw new IllegalArgumentException(String.format(NULL_MSG, key, value));
        }
    }

    public static void entityExists(Object object, String key, Long id) {
        if (object == null) {
            throw new IllegalArgumentException(String.format(NULL_MSG, key, id));
        }
    }



}
