package com.example.hospitalmanagement;

public class Room {
    private int rNo;
    private String rType;
    private String status;
    public Room(int rNo, String rType, int status) {
        this.rNo = rNo;
        this.rType = rType;
        this.status = (status == 0) ? "Occupied" : "Available";
    }
    public int getRNo() {
        return rNo;
    }
    public String getRType() {
        return rType;
    }
    public String getStatus() {
        return status;
    }
}
