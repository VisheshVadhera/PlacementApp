package com.vishesh.tpc_stud.dashboard.models;

import java.util.List;

/**
 * Created by vishesh on 24/2/17.
 */
public class UserProfile {

    private Double gpa;
    private String cvUrl;
    private Course course;
    private List<NetworkProfile> networkProfiles;

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<NetworkProfile> getNetworkProfiles() {
        return networkProfiles;
    }

    public void setNetworkProfiles(List<NetworkProfile> networkProfiles) {
        this.networkProfiles = networkProfiles;
    }
}
