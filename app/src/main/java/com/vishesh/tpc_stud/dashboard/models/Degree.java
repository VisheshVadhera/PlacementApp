package com.vishesh.tpc_stud.dashboard.models;

public enum Degree {

    BACHELORS("Bachelors"),
    MASTERS("Masters"),
    DOCTORATE("Doctorate");

    private final String degreeName;

    Degree(String s) {
        degreeName = s;
    }


    public String getDegreeName() {
        return degreeName;
    }
}
