package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.NewApp;
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

    private final ObjectMapper mapper;

    public AuthServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session;
        if(req.getParameter("action").equals("login")){
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
        }else if(req.getParameter("action").equals("register")) {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
//        List<NewApp> users = new ArrayList<>();
////
////        users.addAll(Arrays.asList(
////            new NewApp("admin", "revature"),
////            new NewApp("rrich", "password"),
////            new NewApp("bserfozo", "password")
////        ));
////
////        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
////
////        String providedUname = (String) credentials.get("uname");
////        String providedPword = (String) credentials.get("pword");
//////        System.out.println(providedUname + " " + providedPword);
////
////        for (NewApp user: users){
////            if(providedUname == user.getUname() && providedPword == user.getPword()){
////                System.out.println("[LOG] - Found User");
//                HttpSession session = req.getSession();
////                session.setAttribute("auth-user", user);
////
////                resp.setStatus(204);
////                return;
////            }
////        }
////
////        resp.setStatus(400);
////        resp.setContentType("application/json");

////        HashMap<String, Object> errorMessage = new HashMap<>();
////        errorMessage.put("Status code", 400);
////        errorMessage.put("Message", "No user found with provided credentials");
////        errorMessage.put("Timestamp", LocalDateTime.now().toString());
////        resp.getWriter().write(mapper.writeValueAsString(errorMessage));
//            System.out.println("[LOG] - AuthServlet received a GET request at " + LocalDateTime.now());
////        Employee someUser = new NewApp("admin", "password");
//            Employee loggedUser = mapper.readValue(req.getInputStream(), Employee.class);
//            Employee employee = es.login(loggedUser.getUsername(), loggedUser.getPassword());
//            String payload = mapper.writeValueAsString(employee);
//            System.out.println("[LOG] - was request filtered? " + req.getAttribute("was-filtered"));//
////        List<NewApp> users = new ArrayList<>(); // Stores objects in a list
////        NewApp someUser2 = new NewApp("rev", "password");
////        users.add(someUser2);
////        users.add(someUser);
////        String respPayload = mapper.writeValueAsString(someUser); // sends that list back as string
//            resp.setStatus(200);
//            resp.setContentType("application/json");
////        resp.getWriter().write(respPayload);