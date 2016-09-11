package com.ogresolutions.kaogire.smarthouse.objects;

import java.util.Date;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class Complaint {
    int id;
    String voteHead, detail,category;
    Date time, dAddressed;
    boolean addressed;
    public Complaint(){}
    public Complaint(int id, String category, String voteHead, String detail, Date time, boolean addressed){
        this.id = id;
        this.category = category;
        this.detail = detail;
        this.time = time;
        this.voteHead = voteHead;
        this.addressed = addressed;
    }

    public Complaint(int id, String voteHead, String category, String detail, Date time, boolean addressed, Date dAddressed){
        this.id = id;
        this.category = category;
        this.detail = detail;
        this.time = time;
        this.voteHead = voteHead;
        this.addressed = addressed;
        this.dAddressed = dAddressed;
    }

    public Date getdAddressed() {
        return dAddressed;
    }

    public void setdAddressed(Date dAddressed) {
        this.dAddressed = dAddressed;
    }

    public void setCategory(String title) {
        this.category = title;
    }

    public void setVoteHead(String voteHead) {
        this.voteHead = voteHead;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTime() {
        return time;
    }

    public String getDetail() {
        return detail;
    }

    public String getVoteHead() {
        return voteHead;
    }

    public String getCategory() {
        return category;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isAddressed() {
        return addressed;
    }

    public void setAddressed(boolean addressed) {
        this.addressed = addressed;
    }
}
