package com.revature.dao;

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
        Employee employ = new Employee();



        try (Connection conn = connectionUtil.getConnection()){

            String sql = "SELECT * FROM employee WHERE username = ?";


            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);


            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){

                rs.next();

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

        Employee employee = new Employee();

        try (Connection conn = connectionUtil.getConnection()){ // connecting with try-with-resources
            String sql = "INSERT INTO employee (fname, lname, username, password, email) VALUES (?, ?, ?, ?, ?) RETURNING *"; // our SQL statement to create
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

                employee = new Employee(id,receivedFname, receivedLname,receivedUname,pw,e);
            }

        } catch(SQLException e){
            System.out.println("Username taken. Please try again.");
//            exit(0);
            // e.printStackTrace();
            return null;
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {


        Connection conn = connectionUtil.getConnection();


        List<Employee> employees = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM employee";

            ResultSet rs = stmt.executeQuery(sql);



            while (rs.next()){
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                Employee employ = new Employee(id, fname, lname, username, password, email);

                employees.add(employ);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


}