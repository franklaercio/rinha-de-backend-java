package com.github.rinha.domain.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";

    private static final String TIME_ZONE = "UTC";

    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatToString(Timestamp timestamp) {
        return timestamp.toLocalDateTime()
                .atZone(ZoneId.of(TIME_ZONE))
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String nowUTCToString() {
        return LocalDateTime.now()
            .atZone(ZoneId.of("UTC"))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"));
    }

    public static Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.of(TIME_ZONE)).toLocalDateTime());
    }
}
