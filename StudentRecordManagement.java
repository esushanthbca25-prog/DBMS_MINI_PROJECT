/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentrecordmanagement;

/**
 *
 * @author Sushanth
 */


import java.sql.*;
import java.util.Scanner;

public class StudentRecordManagement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String password = "ChooseYourOwnPassword123!";

        try (Connection con =
                DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Student Record Management");

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Course: ");
                    String course = sc.nextLine();

                    System.out.print("Semester: ");
                    int semester = sc.nextInt();
                    sc.nextLine();

                    String sql = "INSERT INTO students "
                            + "VALUES (?, ?, ?, ?, ?)";

                    try (PreparedStatement ps =
                            con.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setString(3, email);
                        ps.setString(4, course);
                        ps.setInt(5, semester);
                        ps.executeUpdate();
                        System.out.println("Student added!");
                    }

                } else if (choice == 2) {
                    String sql = "SELECT * FROM students";

                    try (Statement st = con.createStatement();
                         ResultSet rs = st.executeQuery(sql)) {

                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("student_id") + " | "
                                + rs.getString("name") + " | "
                                + rs.getString("email") + " | "
                                + rs.getString("course") + " | "
                                + rs.getInt("semester"));
                        }
                    }

                } else if (choice == 3) {
                    System.out.print("Student ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New name: ");
                    String name = sc.nextLine();

                    System.out.print("New email: ");
                    String email = sc.nextLine();

                    System.out.print("New course: ");
                    String course = sc.nextLine();

                    System.out.print("New semester: ");
                    int semester = sc.nextInt();
                    sc.nextLine();

                    String sql = "UPDATE students SET name=?, "
                            + "email=?, course=?, semester=? "
                            + "WHERE student_id=?";

                    try (PreparedStatement ps =
                            con.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, course);
                        ps.setInt(4, semester);
                        ps.setInt(5, id);

                        int rows = ps.executeUpdate();
                        System.out.println(rows > 0
                                ? "Student updated!"
                                : "Student ID not found.");
                    }

                } else if (choice == 4) {
                    System.out.print("Student ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    String sql =
                            "DELETE FROM students WHERE student_id=?";

                    try (PreparedStatement ps =
                            con.prepareStatement(sql)) {
                        ps.setInt(1, id);

                        int rows = ps.executeUpdate();
                        System.out.println(rows > 0
                                ? "Student deleted!"
                                : "Student ID not found.");
                    }

                } else if (choice == 5) {
                    System.out.println("Application closed.");
                    break;

                } else {
                    System.out.println("Invalid choice!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}


