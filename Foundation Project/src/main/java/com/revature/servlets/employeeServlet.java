package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.NewApp;
import com.revature.service.EmployeeServiceAPI;
import com.revature.service.employeeService;
//import jdk.nashorn.internal.ir.LiteralNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(value = "/login", loadOnStartup = 2)
@WebServlet("/employee")
public class employeeServlet extends HttpServlet{

    public void init() throws ServletException {
        System.out.println("[LOG] - employeeServlet initiated!");
        System.out.println("[LOG] - Context param test-init-key: " + this.getServletContext().getInitParameter("test-context-key"));

    }

    @Override
    public void destroy() {
        System.out.println("[LOG] - employeeServlet destroyed");
    }

    ObjectMapper mapper = new ObjectMapper();

    Employee employee = new Employee();

    EmployeeServiceAPI es = new EmployeeServiceAPI();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        es.getAllEmployees();
//        es.login();
//        if(employee.getUsername() != null){
//            resp.setStatus(200);
//
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("[LOG] - employeeServlet received a POST request at " + LocalDateTime.now());

        // make user input into account
        Employee newUser = mapper.readValue(req.getInputStream(), Employee.class); // read value in and store in NewApp object
        System.out.println(newUser); // ready to be sent to service layer for validation
        Employee employee = es.register(newUser.getFname(), newUser.getLname(), newUser.getUsername(), newUser.getPassword(), newUser.getEmail());
        String payload = mapper.writeValueAsString(employee);
        if(!payload.equals("null")){
            resp.getWriter().write(payload);
            System.out.println("New User registered!");
            resp.setStatus(204);
//        } else if {
//            newUser.getUsername().equals()
//
        } else {
            resp.getWriter().write("Failed to create user");
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - employeeServlet received a GET request at " + LocalDateTime.now());


//        Employee someUser = new NewApp("admin", "password");

        Employee loggedUser = mapper.readValue(req.getInputStream(), Employee.class);
        String payload = mapper.writeValueAsString(loggedUser);
//        if(loggedUser.getUsername() && loggedUser.getPassword()){
//
//        }

        System.out.println("[LOG] - was request filtered? " + req.getAttribute("was-filtered"));
//        String respPayload = mapper.writeValueAsString(someUser); // converting java object into string
//
//        List<NewApp> users = new ArrayList<>(); // Stores objects in a list
//        NewApp someUser2 = new NewApp("rev", "password");
//        users.add(someUser2);
//        users.add(someUser);

//        String respPayload = mapper.writeValueAsString(someUser); // sends that list back as string





        resp.setStatus(200);
        resp.setContentType("application/json");
//        resp.getWriter().write(respPayload);
    }
}
