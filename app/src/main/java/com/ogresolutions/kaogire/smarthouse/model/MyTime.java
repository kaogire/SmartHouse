package com.ogresolutions.kaogire.smarthouse.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Njuguna on 14-Jul-16.
 */
public class MyTime {
    Date date;
    String sDate;
    public MyTime(Date date){
        this.date = date;
    }

    public MyTime (String date){
        this.sDate = date;
    }

    public static Date stringToDate(String date){
        Date theDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            theDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return theDate;
    }
    public String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String displayDate(Date myDate){
        Calendar date = Calendar.getInstance();
        date.setTime(myDate);
        String sDate = new SimpleDateFormat("y-mm-dd").format(myDate);
        Calendar now = Calendar.getInstance();

        if (now.DAY_OF_MONTH - date.DAY_OF_MONTH <= 7){
            SimpleDateFormat sdf = new SimpleDateFormat("E");
            sDate = sdf.format(date.getTime());
        } else if(now.DAY_OF_MONTH - date.DAY_OF_MONTH == 0 ) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sDate = sdf.format(date.getTime());
        }
        return sDate;
    }
}
