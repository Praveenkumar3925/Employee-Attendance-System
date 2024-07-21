package com.mysql.EmployeeAttendance;
import java.sql.*;
import java.util.Scanner;

public class Employees {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter hire date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();

        System.out.print("Enter job title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/praveen";
        String dbUsername = "root";
        String dbPassword = "Praveen@123";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_title, department) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            pstmt.setDate(5, Date.valueOf(hireDate));
            pstmt.setString(6, jobTitle);
            pstmt.setString(7, department);

            pstmt.executeUpdate();
            System.out.println("Employee registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
