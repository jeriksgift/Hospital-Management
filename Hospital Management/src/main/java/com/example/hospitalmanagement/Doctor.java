package com.example.hospitalmanagement;

public class Doctor {
    private int dId;
    private String dName;
    private String dSpecialization;
    private String dDoj;

    public Doctor(int dId, String dName, String dSpecialization, String dDoj) {
        this.dId = dId;
        this.dName = dName;
        this.dSpecialization = dSpecialization;
        this.dDoj = dDoj;
    }

    public int getDId() { return dId; }
    public String getDName() { return dName; }
    public String getDSpecialization() { return dSpecialization; }
    public String getDDoj() { return dDoj; }
}
