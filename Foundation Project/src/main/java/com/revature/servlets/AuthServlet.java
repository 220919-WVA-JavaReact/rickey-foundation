package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;
import com.revature.model.NewApp;
import com.revature.model.Ticket;
import com.revature.service.EmployeeServiceAPI;
import com.revature.service.employeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class AuthServlet extends HttpServlet {
    EmployeeServiceAPI es = new EmployeeServiceAPI();

    ticketImplPostgres tip = new ticketImplPostgres();

    private final ObjectMapper mapper;

    public AuthServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session;
        if (req.getParameter("action").equals("login")) {
            Employee loggedemployee = mapper.readValue(req.getInputStream(), Employee.class);
            Employee loggedemploy = es.login(loggedemployee.getUsername(), loggedemployee.getPassword());
            String payload = mapper.writeValueAsString(loggedemploy);
            System.out.println(payload == null);
            if (!payload.equals("null")) {
                resp.getWriter().write(payload);
                session = req.getSession();
                session.setAttribute("auth-user", loggedemploy);
                System.out.println("You have logged in");
                resp.setStatus(200);
//
            } else {
                resp.getWriter().write("Invalid credentials");
                resp.setStatus(400);
            }
        } else if (req.getParameter("action").equals("register")) {
            Employee newUser = mapper.readValue(req.getInputStream(), Employee.class);
            Employee employee = es.register(newUser.getFname(), newUser.getLname(), newUser.getUsername(), newUser.getPassword(), newUser.getEmail());
            String payload = mapper.writeValueAsString(employee);
            if (!payload.equals("null")) {
                resp.getWriter().write(payload);
                session = req.getSession();
                session.setAttribute("auth-user", employee);
                System.out.println("New User registered!");
                resp.setStatus(201);
//
            } else {
                resp.getWriter().write("Failed to create user, username already taken. Try again.");
                resp.setStatus(400);
            }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - AuthServlet initiated");
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            resp.getWriter().write("You have been logged out.");
            resp.setStatus(400);
        }
    }
}

