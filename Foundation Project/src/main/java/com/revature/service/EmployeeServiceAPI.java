package com.revature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.revature.service.employeeService;
import com.revature.util.connectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class EmployeeServiceAPI {
    EmployeeDAOImplPostgres ed = new EmployeeDAOImplPostgres();

    ticketImplPostgres tip = new ticketImplPostgres();







    public Employee register(String fname, String lname, String username, String password, String email) {
        return ed.createEmployee(fname, lname, username, password, email);
    }

    public Employee login(String username, String password) throws JsonProcessingException {
        Employee employee = ed.getByUsername(username);
//        HttpSession session = req.getSession(false);
        if(password.equals(employee.getPassword())){
            System.out.println("You have been logged in!");
            System.out.println(employee);
            return employee;
        } else{

            return null;
        }

    }

    public Employee managerValid(String username){
        Employee manager = ed.getByStatus(username);
        if(manager.getUsername().equals("admin")){
            System.out.println("You are acting as a manager!");
            return manager;
        } else {
            return null;
        }
    }
}
