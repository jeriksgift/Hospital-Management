package com.example.hospitalmanagement;

public class Billing {
    private String medName;
    private int quantity;
    private double price;
    public Billing(String medName, int quantity, double price) {
        this.medName = medName;
        this.quantity = quantity;
        this.price = price;
    }
    public String getMedName() {
        return medName;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
}
