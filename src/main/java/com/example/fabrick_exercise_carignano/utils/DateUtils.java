package com.example.fabrick_exercise_carignano.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date startOfYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); // yesterday
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date endOfYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); // yesterday
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static LocalDate getYesterdayLocalDate(){
        return LocalDate.now().minusDays(1);
    }
}
