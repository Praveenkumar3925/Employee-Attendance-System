package com.mysql.EmployeeAttendance;


import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class GenerateAttendanceReport {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Enter report date (YYYY-MM-DD): ");
        scanner.nextLine();  // consume newline
        String reportDate = scanner.nextLine();

        System.out.print("Enter total hours: ");
        BigDecimal totalHours = scanner.nextBigDecimal();

        String url = "jdbc:mysql://localhost:3306/praveen";
        String dbUsername = "root";
        String dbPassword = "Praveen@123";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "INSERT INTO attendance_reports (employee_id, report_date, total_hours) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, Date.valueOf(reportDate));
            pstmt.setBigDecimal(3, totalHours);

            pstmt.executeUpdate();
            System.out.println("Attendance report generated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
