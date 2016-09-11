package com.ogresolutions.kaogire.smarthouse.objects;

import java.util.Date;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class Service {
    int diff, id;
    String type, amountDue;
    double penalty;
    Boolean settled, overDue;
    Date dateDue, datePaid;

    public Service(){}
    public Service(int id, String type, String amountDue, Date dateDue, int diff){
        this.amountDue = amountDue;
        this.dateDue = dateDue;
        this.id = id;
        this.type = type;
//        this.penalty = penalty;
        this.overDue = diff < 0;
    }

    public Service(int id, String type, String amountDue, Date dateDue, int diff, Date datePaid, boolean settled){
        this.amountDue = amountDue;
        this.id = id;
        this.dateDue = dateDue;
        this.type = type;
        this.penalty = penalty;
        this.overDue = diff < 0;
        this.settled = settled;
        this.datePaid = datePaid;
    }


    public Boolean getOverDue() {
        return overDue;
    }

    public void setOverDue(Boolean overDue) {
        this.overDue = overDue;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getPenalty() {
        return penalty;
    }

    public Boolean getSettled() {
        return settled;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public String getType() {
        return type;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public void setSettled(Boolean settled) {
        this.settled = settled;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public void setType(String type) {
        this.type = type;
    }
}
