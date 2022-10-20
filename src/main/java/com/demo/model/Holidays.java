package com.demo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

// For Hibernate to automatically translate the entity into a table.
// primary key field is required for @Entity 
@Entity
public class Holidays {

    @Id
    private String uid;    
    private Date startDate;
    private Date endDate;
    private String summary;

        
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.startDate = formatter.parse(startDate);
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.endDate = formatter.parse(endDate);
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
  }