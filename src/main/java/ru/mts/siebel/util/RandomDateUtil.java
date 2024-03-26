package ru.mts.siebel.util;

import java.time.LocalDate;

public interface RandomDateUtil {

    static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        System.out.println(LocalDate.of(year, month, day));
        return LocalDate.of(year, month, day);
    }

    static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}
