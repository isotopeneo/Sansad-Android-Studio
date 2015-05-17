package com.jujitsutech.sansad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sujit Devkar on 28-02-2015.
 */
public class DateManipulation {

    private static String getTodayDate;

    public static String findTodayDate() {
        Date date = new Date();
        getTodayDate = new SimpleDateFormat("YYYY-MM-DD").format(date);
        return getTodayDate;
    }

}
