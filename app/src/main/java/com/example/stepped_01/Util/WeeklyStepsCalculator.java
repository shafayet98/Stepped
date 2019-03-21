package com.example.stepped_01.Util;

import java.util.Calendar;

public class WeeklyStepsCalculator {

    public static String getToday(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                return SharedPrefUtility.SUNDAY;
            case Calendar.MONDAY:
                return SharedPrefUtility.MONDAY;
            case Calendar.TUESDAY:
                return SharedPrefUtility.TUESDAY;
            case Calendar.WEDNESDAY:
                return SharedPrefUtility.WEDNESDAY;
            case Calendar.THURSDAY:
                return SharedPrefUtility.THURSDAY;
            case Calendar.FRIDAY:
                return SharedPrefUtility.FRIDAY;
            case Calendar.SATURDAY:
                return SharedPrefUtility.SATURDAY;
        }
        return "";
    }
}
