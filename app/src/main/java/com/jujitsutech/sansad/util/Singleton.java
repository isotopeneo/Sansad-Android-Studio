package com.jujitsutech.sansad.util;

import com.jujitsutech.sansad.bean.Headlines;
import com.jujitsutech.sansad.bean.ParliamentBills;

import java.util.List;

/**
 * Created by Jayesh on 2/21/2015.
 */
public class Singleton {

    public static List<ParliamentBills> data;
    public static List<Headlines> headlinesList;

    public static int countOfResults = 0;
    public static int currentPageNumber = 0;

    public static int totalNumberOfPages() {
        if (countOfResults > 20) {
            return countOfResults / 20;
        } else {
            return 1;
        }
    }
}
