package com.tr.mustafakacar.WordToPhrase.helper;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class DateActions {

    public static long findNextDateByCorrectCount( int currentCorrectCount) {
        LocalDate now = LocalDate.now();
        switch (currentCorrectCount) {

            case 1:
                now.plusWeeks(1);
                break;
            case 2:
                now.plusMonths(1);
                break;
            case 3:
                now.plusMonths(3);
                break;
            case 4:
                now.plusMonths(6);
                break;
            case 5:
                now.plusYears(1);
                break;

        }
        return now.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();

    }

    public static long getStartOfTodayAsMilliseconds() {
        LocalDate today = LocalDate.now();
        return today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
    public static long getNowAsMilliseconds() {
        LocalDate today = LocalDate.now();
        return today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
