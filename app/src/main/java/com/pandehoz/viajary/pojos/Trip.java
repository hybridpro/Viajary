package com.pandehoz.viajary.pojos;

import java.util.List;

/**
 * Created by test on 14.03.15.
 */
public class Trip {

    private String tripTitle;

    private String tripFrom;

    private String tripTo;

    private String proposedStartTime;

    private String actualStartTime;

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Expense> getExpensesToRecord() {
        return expensesToRecord;
    }

    public void setExpensesToRecord(List<Expense> expensesToRecord) {
        this.expensesToRecord = expensesToRecord;
    }

    private List<Expense> expensesToRecord;

    public List<Expense> getExpenses() {
        return expensesToRecord;
    }

    public void setExpenses(List<Expense> expensesToRecord) {
        this.expensesToRecord = expensesToRecord;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getTripFrom() {
        return tripFrom;
    }

    public void setTripFrom(String tripFrom) {
        this.tripFrom = tripFrom;
    }

    public String getTripTo() {
        return tripTo;
    }

    public void setTripTo(String tripTo) {
        this.tripTo = tripTo;
    }

    public String getProposedStartTime() {
        return proposedStartTime;
    }

    public void setProposedStartTime(String proposedStartTime) {
        this.proposedStartTime = proposedStartTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }
}
