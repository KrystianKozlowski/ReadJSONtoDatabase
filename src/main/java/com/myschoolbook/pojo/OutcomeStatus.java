package com.myschoolbook.pojo;

import javax.persistence.*;

/**
 * Created by Krystian on 2017-05-31.
 */
@Embeddable
public class OutcomeStatus {

    @Column(name = "outcomecategory")
    private String category;
    @Column(name = "outcomedate")
    private String date;

    public OutcomeStatus() {
    }

    public OutcomeStatus(String category, String date) {
        this.category = category;
        this.date = date;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
