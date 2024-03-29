package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.service.employeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class ManagerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - ManagerServlet initiated");
        System.out.println("[LOG] - Init param test-init-key: " + this.getServletConfig().getInitParameter("test-init-key"));
        System.out.println("[LOG] - Context param test-init-key: " + this.getServletContext().getInitParameter("test-context-key"));

    }

    @Override
    public void destroy() {
        System.out.println("[LOG] - ManagerServlet has been destroyed");
    }

    Employee manager = new Employee();

    employeeService es = new employeeService();

    private final ObjectMapper mapper;

    private final ticketImplPostgres tp;

    public ManagerServlet(ObjectMapper mapper, ticketImplPostgres tp) {
        this.mapper = mapper;
        this.tp = tp;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null){
            resp.setStatus(400);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("Status code", 400);
            errorMessage.put("Message", "No user found with provided credentials");
            errorMessage.put("Timestamp", LocalDateTime.now().toString());

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        }
        System.out.println("[LOG] - ManagerServlet received a GET request at " + LocalDateTime.now());
    }


}

