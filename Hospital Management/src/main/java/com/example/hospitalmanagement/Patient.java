package com.example.hospitalmanagement;

public class Patient {
    private int pId;
    private String pName;
    private int pAge;
    private String pGender;
    private String pDisease;
    private int pFees;
    private int pRno;
    private String pAdmitDate;
    public Patient(int pId, String pName, int pAge, String pGender, String pDisease, int pFees, int pRno, String pAdmitDate) {
        this.pId = pId;
        this.pName = pName;
        this.pAge = pAge;
        this.pGender = pGender;
        this.pDisease = pDisease;
        this.pFees = pFees;
        this.pRno = pRno;
        this.pAdmitDate = pAdmitDate;
    }
    public int getPId() { return pId; }
    public String getPName() { return pName; }
    public int getPAge() { return pAge; }
    public String getPGender() { return pGender; }
    public String getPDisease() { return pDisease; }
    public int getPFees() { return pFees; }
    public int getPRno() { return pRno; }
    public String getPAdmitDate() { return pAdmitDate; }
}
