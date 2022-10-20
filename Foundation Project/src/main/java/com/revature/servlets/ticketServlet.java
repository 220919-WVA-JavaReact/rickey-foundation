package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.service.EmployeeServiceAPI;
import com.revature.service.TicketServiceAPI;
import com.revature.service.ticketService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/tickets")
public class ticketServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    ObjectMapper mapper = new ObjectMapper();


    EmployeeServiceAPI es = new EmployeeServiceAPI();

    TicketServiceAPI tsa = new TicketServiceAPI();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LOG - ticketServlet received a GET request at " + LocalDateTime.now());
//        ts.getAllTickets(employee);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - ticketServlet received a POST request at " + LocalDateTime.now());
        HttpSession session = req.getSession(false);
        if(session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");

            if (req.getParameter("action").equals("submit-ticket")) {
                Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);
                Ticket tick = tsa.create(ticket.getAmount(), ticket.getReason(), loggedInEmployee);
                String payload = mapper.writeValueAsString(tick);
                if(!payload.equals("null")){
                    resp.getWriter().write(payload);
                    resp.setStatus(201);
                } else{
                    resp.getWriter().write("Ticket wasn't created");
                    resp.setStatus(200);
                }


            }
        } else{
            resp.getWriter().write("You must be logged in to create a ticket");
            resp.setStatus(403);
        }

    }

}
