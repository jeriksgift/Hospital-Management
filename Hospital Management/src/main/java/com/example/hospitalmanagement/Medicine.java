package com.example.hospitalmanagement;

public class Medicine {
    private int medId;
    private String medName;
    private double price;

    public Medicine(int medId, String medName, double price) {
        this.medId = medId;
        this.medName = medName;
        this.price = price;
    }

    public int getMedId() { return medId; }
    public String getMedName() { return medName; }
    public double getPrice() { return price; }
}
