package com.example.hospitalmanagement;

public class Ambulance {
    private int driId;
    private String driName;
    private String vNo;
    public Ambulance(int driId, String driName, String vNo) {
        this.driId = driId;
        this.driName = driName;
        this.vNo = vNo;
    }
    public int getDriId() {
        return driId;
    }
    public String getDriName() {
        return driName;
    }
    public String getVNo() {
        return vNo;
    }
}
