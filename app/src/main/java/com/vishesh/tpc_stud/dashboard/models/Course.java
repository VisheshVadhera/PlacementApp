package com.vishesh.tpc_stud.dashboard.models;

/**
 * Created by vishesh on 24/2/17.
 */

public class Course {

    private Degree degree;
    private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
