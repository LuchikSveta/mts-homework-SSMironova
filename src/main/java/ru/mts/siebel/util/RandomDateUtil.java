package ru.mts.siebel.util;

import java.time.LocalDate;

public interface RandomDateUtil {

    static LocalDate createRandomDate(final int startYear, final int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    static int createRandomIntBetween(final int start, final int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}
