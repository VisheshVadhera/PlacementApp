package com.vishesh.tpc_stud.dashboard.models;

import org.joda.time.LocalDate;

/**
 * Created by vishesh on 24/2/17.
 */

public class RecruiterModel {

    private int id;
    private String name;
    private String description;
    private LocalDate processDate;
    private JobOffer jobOffer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDate processDate) {
        this.processDate = processDate;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

}
