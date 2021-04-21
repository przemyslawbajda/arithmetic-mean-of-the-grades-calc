package com.example.lab1;

public class ModelOceny {

    private String subjectName;
    private int grade;

    public ModelOceny(String subjectName, int grade){
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
