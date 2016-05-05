package com.ogresolutions.kaogire.smarthouse.objects;

import java.util.Date;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class Service {
    String type, amountDue;
    Boolean settled = false;
    Date timeDue, timePaid;

    public Service(){}
    public Service(String type, String amountDue, Date timeDue){
        this.amountDue = amountDue;
        this.timeDue = timeDue;
        this.type = type;
    }

    public Boolean getSettled() {
        return settled;
    }

    public Date getTimeDue() {
        return timeDue;
    }

    public Date getTimePaid() {
        return timePaid;
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

    public void setTimeDue(Date timeDue) {
        this.timeDue = timeDue;
    }

    public void setTimePaid(Date timePaid) {
        this.timePaid = timePaid;
    }

    public void setType(String type) {
        this.type = type;
    }
}
