package com.revature.servlets;

import com.revature.model.Employee;
import com.revature.service.employeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerServlet extends HttpServlet {

    Employee manager = new Employee();

    employeeService es = new employeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        es.login();
        if(manager.getUsername() == "admin"){
            resp.setStatus(200);
        } else{
            resp.setStatus(403);
            System.out.println("Unauthorized");
        }
    }
}
