package com.example.hospitalmanagement;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;

public class PrintDocument {
    public void generatePDF(ObservableList<Billing> tableData, String patientName) {
        String dest = "JavaHospitalBill_" + System.currentTimeMillis() + ".pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            document.setMargins(20, 20, 20, 20);
            document.add(new Paragraph("Java Hospital Bill Receipt"));
            document.add(new Paragraph("Date: " + new java.util.Date()));
            document.add(new Paragraph("--------------------------------------------------------------\n"));

            try{
                DBConnection dbConnection = new DBConnection();
                Connection conn = dbConnection.getConnection();
                String query = "SELECT p_name, p_age, p_gender FROM patients WHERE p_name = ?";
                java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, patientName);
                java.sql.ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    String name = rs.getString("p_name");
                    int age = rs.getInt("p_age");
                    String gender = rs.getString("p_gender");
                    document.add(new Paragraph("Patient Name: " + name));
                    document.add(new Paragraph("Age: " + age));
                    document.add(new Paragraph("Gender: " + gender));
                }
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            document.add(new Paragraph("--------------------------------------------------------------\n"));
            PdfPTable table = new PdfPTable(3);
            table.addCell("Medicine");
            table.addCell("Quantity");
            table.addCell("Price");
            double total = 0.0;
            for (Billing item : tableData) {
                table.addCell(item.getMedName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.format("%.2f", item.getPrice()));
                total += item.getPrice();
            }

            document.add(table);
            document.add(new Paragraph("--------------------------------------------------------------\n"));
            document.add(new Paragraph("Total: ₹" + total + "\n"));

            document.close();
            System.out.println("PDF Generated: " + dest);

            try{
                DBConnection dbConnection = new DBConnection();
                Connection conn = dbConnection.getConnection();
                String query = "UPDATE patients SET p_fees = p_fees + ? WHERE p_name = ?";
                java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setDouble(1, total);
                pstmt.setString(2, patientName);
                pstmt.executeUpdate();
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            // Optional: open automatically
            java.awt.Desktop.getDesktop().open(new File(dest));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
