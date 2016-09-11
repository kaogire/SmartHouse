package com.ogresolutions.kaogire.smarthouse.objects;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class Notice {
    int id;
    String houseNo;
    String title;
    String body;
    Date timeUp;
    boolean seen;
//    int counter;

    public Notice (){}

    public Notice(int id, String houseNo, String title, String body, String timeUp){
        this.body = body;
        this.id = id;
        this.houseNo = houseNo;
        this.title = title;
        this.timeUp = stringToDate(timeUp);
    }

    public Date getTimeUp() {
        return timeUp;
    }

    public String getBody() {
        return body;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public void setTimeUp(Date timeUp) {
        this.timeUp = timeUp;
    }

    public void setTimeUp(String timeUp) {
        this.timeUp = stringToDate(timeUp);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    //    public int getCounter() {
//        return counter;
//    }

//    public void setCounter(int counter) {
//        this.counter = counter;
//    }

    public Date stringToDate(String date){
        if(date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
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
