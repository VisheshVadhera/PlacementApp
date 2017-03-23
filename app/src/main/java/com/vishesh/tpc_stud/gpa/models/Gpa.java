package com.vishesh.tpc_stud.gpa.models;

import java.util.List;

public class Gpa {

    private Double gpa;
    private List<SemesterGrade> semesterGrades;

    public List<SemesterGrade> getSemesterGrades() {
        return semesterGrades;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public void setSemesterGrades(List<SemesterGrade> semesterGrades) {
        this.semesterGrades = semesterGrades;
    }
}
