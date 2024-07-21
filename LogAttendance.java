package com.mysql.EmployeeAttendance;


import java.sql.*;
import java.util.Scanner;

public class LogAttendance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Enter check-in time (YYYY-MM-DD HH:MM:SS): ");
        scanner.nextLine();  // consume newline
        String checkInTime = scanner.nextLine();

        System.out.print("Enter check-out time (YYYY-MM-DD HH:MM:SS): ");
        String checkOutTime = scanner.nextLine();

        System.out.print("Enter attendance date (YYYY-MM-DD): ");
        String attendanceDate = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/praveen";
        String dbUsername = "root";
        String dbPassword = "Praveen@123";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "INSERT INTO attendance (employee_id, check_in_time, check_out_time, attendance_date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            pstmt.setTimestamp(2, Timestamp.valueOf(checkInTime));
            pstmt.setTimestamp(3, Timestamp.valueOf(checkOutTime));
            pstmt.setDate(4, Date.valueOf(attendanceDate));

            pstmt.executeUpdate();
            System.out.println("Attendance logged successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
