package com.revature.dao;

//public class EmplPostgres implements employeeDAO{
//
//}
//package org.example.dao;

import com.revature.model.Employee;
import com.revature.util.connectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static java.lang.System.exit;


public class EmployeeDAOImplPostgres implements employeeDAO{

    @Override
    public Employee getByUsername(String username) {
        Employee employ = new Employee(); // used to store information that is pulled back from username


        // I'm going to use a try-with-resources block to make sure I close my connection once done
        try (Connection conn = connectionUtil.getConnection()){

            // write the SQL statment we want to use
            String sql = "SELECT * FROM employee WHERE username = ?";
            // I put a ? anywhere I want to enter information

            // prepare the statement
            PreparedStatement stmt = conn.prepareStatement(sql);

            // set values for ?s
            stmt.setString(1, username);

            // different when receiving multiple values
            ResultSet rs;

            // compare value since only have one result
            if ((rs = stmt.executeQuery()) != null){ // if rs has value we found info

                rs.next(); // moves to next set of values

                // take info from rs.next() and store in employee

                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String receivedUsername = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                // create employee object to return
                employ = new Employee(id,fname,lname,receivedUsername,password,email);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }



        return employ;


    }

    @Override
    public Employee createEmployee(String fname, String lname, String username, String password, String email) {

        Employee employ = new Employee(); // blank teacher object to store all info

//        try (Connection conn = connectionUtil.getConnection()){
//            String sql = "SELECT * FROM employee WHERE username = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, username);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()){
//
//            }
//
//        }



//        Connection conn = connectionUtil.getConnection(); // attempting to connect to db
        try (Connection conn = connectionUtil.getConnection()){ // connecting with try-with-resources
            String sql = "INSERT INTO employee (fname, lname, username, \"password\", email) VALUES (?, ?, ?, ?, ?) RETURNING *"; // our SQL statement to create
            PreparedStatement stmt = conn.prepareStatement(sql); // our prepared statement
            // setting values for ?s
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.setString(5, email);

            ResultSet rs; // ResultSet object

            if ((rs = stmt.executeQuery()) != null){
                // if 'if' statement runs the query exist
                rs.next();

                int id = rs.getInt("id");
                String receivedFname = rs.getString("fname");
                String receivedLname = rs.getString("lname");
                String receivedUname = rs.getString("username");
                String pw = rs.getString("password");
                String e = rs.getString("email");

                // use this for user validation
                /*SELECT 1
                FROM table_name
                WHERE unique_key = value;
                 */

                //create a employee object
                employ = new Employee(id,receivedFname, receivedLname,receivedUname,pw,e);
            }

        } catch(SQLException e){
            System.out.println("Username taken. Please try again.");
            exit(0);
            // e.printStackTrace();
        }

        return employ;
    }

    @Override
    public List<Employee> getAllEmployees() {

        // SQL statement to get all employees back from our employees table; SELECT * FROM employee
        // first connect with connection object

        Connection conn = connectionUtil.getConnection();
        // SQL statement

        // to store all employees create an empty list and store the info inside it
        List<Employee> employees = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            // all logic goes inside of the try block

            String sql = "SELECT * FROM employee";

            ResultSet rs = stmt.executeQuery(sql); // this is running our statement on our databasse



            while (rs.next()){
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                // Create an employee object to store the info every loop
                Employee employ = new Employee(id, fname, lname, username, password, email);

                // System.out.println(employ);

                // Add resulting item to list of teachers

                employees.add(employ);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


}