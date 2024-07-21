package com.mysql.EmployeeAttendance;

import java.sql.*;
import java.util.Scanner;

public class FetchAllEmployeeData {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.println();

        String url = "jdbc:mysql://localhost:3306/praveen";
        String dbUsername = "root";
        String dbPassword = "Praveen@123";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Fetch employee details
            fetchEmployeeDetails(conn, employeeId);
            // Fetch attendance details
            fetchAttendanceDetails(conn, employeeId);
            // Fetch attendance reports
            fetchAttendanceReports(conn, employeeId);
            // Fetch leave management details
            fetchLeaveManagementDetails(conn, employeeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fetchEmployeeDetails(Connection conn, int employeeId) throws SQLException {
        String query = "SELECT * FROM employees WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Employee Details:");
                System.out.println();
                System.out.println("Employee ID: " + rs.getInt("employee_id"));
                System.out.println("First Name: " + rs.getString("first_name"));
                System.out.println("Last Name: " + rs.getString("last_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone Number: " + rs.getString("phone_number"));
                System.out.println("Hire Date: " + rs.getDate("hire_date"));
                System.out.println("Job Title: " + rs.getString("job_title"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println();
            } else {
                System.out.println("Employee not found!");
                System.out.println();
            }
        }
    }

    private static void fetchAttendanceDetails(Connection conn, int employeeId) throws SQLException {
        String query = "SELECT * FROM attendance WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Attendance Details:");
            while (rs.next()) {
                System.out.println("Attendance ID: " + rs.getInt("attendance_id"));
                System.out.println("Check-in Time: " + rs.getTimestamp("check_in_time"));
                System.out.println("Check-out Time: " + rs.getTimestamp("check_out_time"));
                System.out.println("Attendance Date: " + rs.getDate("attendance_date"));
                System.out.println();
            }
        }
    }

    private static void fetchAttendanceReports(Connection conn, int employeeId) throws SQLException {
        String query = "SELECT * FROM attendance_reports WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Attendance Reports:");
            while (rs.next()) {
                System.out.println("Report ID: " + rs.getInt("report_id"));
                System.out.println("Report Date: " + rs.getDate("report_date"));
                System.out.println("Total Hours: " + rs.getBigDecimal("total_hours"));
                System.out.println();
            }
        }
    }

    private static void fetchLeaveManagementDetails(Connection conn, int employeeId) throws SQLException {
        String query = "SELECT * FROM leave_management WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Leave Management Details:");
            while (rs.next()) {
                System.out.println("Leave ID: " + rs.getInt("leave_id"));
                System.out.println("Leave Start Date: " + rs.getDate("leave_start_date"));
                System.out.println("Leave End Date: " + rs.getDate("leave_end_date"));
                System.out.println("Leave Type: " + rs.getString("leave_type"));
                System.out.println("Leave Status: " + rs.getString("leave_status"));
                System.out.println();
            }
        }
    }
}
