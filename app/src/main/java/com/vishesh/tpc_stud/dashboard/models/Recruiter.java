package com.vishesh.tpc_stud.dashboard.models;

import org.joda.time.LocalDate;

import java.util.List;

public class Recruiter {

    private String name;
    private String description;
    private List<JobOffer> jobOffers;
    private LocalDate processDate;

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

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
}
