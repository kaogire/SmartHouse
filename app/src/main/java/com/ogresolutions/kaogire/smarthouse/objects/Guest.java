package com.ogresolutions.kaogire.smarthouse.objects;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class Guest {
    int id;
    Date timeIn;
    Date timeOut;
    String name;

    public Guest(){}

    public Guest(String name, Date timeIn, Date timeOut){
        this.name = name;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }
    public Guest(String name){
        this.name = name;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public String getName() {
        return name;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeIn(Date timeIn) {

        this.timeIn = timeIn;
    }
    public void setTimeIn(String timeIn) {

        this.timeIn = stringToDate(timeIn);
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }
    public void setTimeOut(String timeOut) {
        this.timeOut = stringToDate(timeOut);
    }

    public Date stringToDate(String date){
        if(date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy HH:mm");
        ParsePosition pos = new ParsePosition(0);
        Date myDate = sdf.parse(date, pos);
        return myDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
