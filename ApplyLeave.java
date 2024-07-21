package com.mysql.EmployeeAttendance;


import java.sql.*;
import java.util.Scanner;

public class ApplyLeave {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Enter leave start date (YYYY-MM-DD): ");
        scanner.nextLine();  // consume newline
        String leaveStartDate = scanner.nextLine();

        System.out.print("Enter leave end date (YYYY-MM-DD): ");
        String leaveEndDate = scanner.nextLine();

        System.out.print("Enter leave type: ");
        String leaveType = scanner.nextLine();

        System.out.print("Enter leave status: ");
        String leaveStatus = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/praveen";
        String dbUsername = "root";
        String dbPassword = "Praveen@123";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "INSERT INTO leave_management (employee_id, leave_start_date, leave_end_date, leave_type, leave_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, Date.valueOf(leaveStartDate));
            pstmt.setDate(3, Date.valueOf(leaveEndDate));
            pstmt.setString(4, leaveType);
            pstmt.setString(5, leaveStatus);

            pstmt.executeUpdate();
            System.out.println("Leave applied successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
