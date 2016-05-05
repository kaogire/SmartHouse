package com.ogresolutions.kaogire.smarthouse.objects;

import java.util.Date;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class Complaint {
    String houseNo,body,title;
    Date time;
    public Complaint(){}
    public Complaint(String houseNo, String body, String title, Date time){
        this.title = title;
        this.body = body;
        this.time = time;
        this.houseNo = houseNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTime() {
        return time;
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

    public void setTime(Date time) {
        this.time = time;
    }
}
