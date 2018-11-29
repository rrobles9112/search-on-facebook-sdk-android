package com.usc.searchonfb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by adarsh on 4/17/2017.
 */

public class DateUtils {

    public static String getFormattedDate(String mDateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        Date result;
        String value = mDateString;
        try {
            result = df.parse(mDateString);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            value = sdf.format(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
