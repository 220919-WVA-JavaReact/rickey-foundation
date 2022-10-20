package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.NewApp;
import com.revature.service.employeeService;

import javax.servlet.ServletException;
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

    Employee employee = new Employee();
    employeeService es = new employeeService();

    private final ObjectMapper mapper;

    public AuthServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewApp> users = new ArrayList<>();

        users.addAll(Arrays.asList(
            new NewApp("admin", "revature"),
            new NewApp("rrich", "password"),
            new NewApp("bserfozo", "password")
        ));

        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);

        String providedUname = (String) credentials.get("uname");
        String providedPword = (String) credentials.get("pword");
//        System.out.println(providedUname + " " + providedPword);

        for (NewApp user: users){
            if(providedUname == user.getUname() && providedPword == user.getPword()){
                System.out.println("[LOG] - Found User");


                HttpSession session = req.getSession();
                session.setAttribute("auth-user", user);

                resp.setStatus(204);
                return;
            }
        }

        resp.setStatus(400);
        resp.setContentType("application/json");

        HashMap<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("Status code", 400);
        errorMessage.put("Message", "No user found with provided credentials");
        errorMessage.put("Timestamp", LocalDateTime.now().toString());

        resp.getWriter().write(mapper.writeValueAsString(errorMessage));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        es.login();
    }
}
