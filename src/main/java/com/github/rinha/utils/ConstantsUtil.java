package com.github.rinha.utils;

public final class ConstantsUtil {

    private ConstantsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";

    public static final String TIME_ZONE = "UTC";

    public static final String INVALID_REQUEST = "Invalid request";

    public static final String TRANSACTION_TYPE_DEBIT = "d";

    public static final String TRANSACTION_TYPE_CREDIT = "c";
}
