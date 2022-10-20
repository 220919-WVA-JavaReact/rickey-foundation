package com.revature.service;

import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.model.Employee;
import com.revature.service.employeeService;
import com.revature.util.connectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceAPI {
    EmployeeDAOImplPostgres ed = new EmployeeDAOImplPostgres();

//    employeeService es = new employeeService();

    public String login(String uname, String pword){
        Employee employee = new Employee();
        if(employee.getUsername().equals(uname) && employee.getPassword().equals(pword)){
            System.out.println("You have been logged in!");
        } else{
            System.out.println("Invalid login");
            return null;
        }
        return uname;
    }


    public Employee register(String fname, String lname, String username, String password, String email) {
        return ed.createEmployee(fname, lname, username, password, email);
    }
}
