package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ticketImplPostgres;
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
import java.util.HashMap;
import java.util.List;

@WebServlet("/tickets")
public class ticketServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    ObjectMapper mapper = new ObjectMapper();


    ticketImplPostgres tip = new ticketImplPostgres();

    TicketServiceAPI tsa = new TicketServiceAPI();

    ticketService ts = new ticketService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LOG - ticketServlet received a GET request at " + LocalDateTime.now());
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (!loggedInEmployee.getUsername().equals("admin")) {
                if (req.getParameter("action").equals("view-my-tickets")) {
                    List<Ticket> tickets = tip.getTicketByEmployeeId(loggedInEmployee);
                    if (req.getParameter("status").equals("pending")) {
                        List<Ticket> pendingTickets = tip.getTicketByStatus(loggedInEmployee);
                        if (pendingTickets.size() < 1) {
                            resp.getWriter().write("You currently have zero pending tickets");
                        } else {
                            String payload = mapper.writeValueAsString(pendingTickets);
                            resp.getWriter().write(payload);
                            resp.setStatus(200);
                            resp.setContentType("application/json");
                        }
                    }
                    if (req.getParameter("status").equals("all")) {
                        List<Ticket> getAllTickets = tip.getTicketByEmployeeId(loggedInEmployee);
                        if (getAllTickets.size() < 1) {
                            resp.getWriter().write("You have no previous tickets");
                        } else {
                            String payload = mapper.writeValueAsString(getAllTickets);
                            resp.getWriter().write(payload);
                            resp.setStatus(200);
                            resp.setContentType("application/json");
                        }
                        if (tickets.size() < 1) {
                            resp.getWriter().write("You currently have zero tickets");
                        } else {
                            String payload = mapper.writeValueAsString(tickets);
                            resp.getWriter().write(payload);
                            resp.setStatus(200);
                            resp.setContentType("application/json");
                        }
                    }
                }
            }else {
                    if (req.getParameter("action").equals("view-all-tickets")) {
                        List<Ticket> newTickets = tsa.getTicketsByStatus("Pending");
                        if (newTickets.size() > 0) {
                            String payload = mapper.writeValueAsString(newTickets);
                            resp.getWriter().write(payload);
                            resp.setContentType("application/json");
                        } else {
                            resp.getWriter().write("There are no pending tickets");
                        }


                    }

            }

            }

        }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (loggedInEmployee.getUsername().equals("admin")) {
                Ticket jsonTicket = mapper.readValue(req.getInputStream(), Ticket.class);
                Ticket ticket = tsa.getTicketById(jsonTicket.getId());
                if(ticket.getStatus().equals("Pending")){
                    if(req.getParameter("action").equals("approve")){
                        Ticket approveTicket = tsa.updateTicket(ticket.getId(), "Approved");
                        String payload = mapper.writeValueAsString(approveTicket);
                        resp.getWriter().write(payload);
                        resp.setContentType("application/json");
                    } else if (req.getParameter("action").equals("deny")) {
                        Ticket denyTicket = tsa.updateTicket(ticket.getId(), "Denied");
                        String payload = mapper.writeValueAsString(denyTicket);
                        resp.getWriter().write(payload);
                        resp.setContentType("application/json");
                    }
                }else{
                    resp.getWriter().write("This ticket has already been processed.");
                }
            } else {
                resp.getWriter().write("You must be logged in as a manager");
                resp.setStatus(401);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - ticketServlet received a POST request at " + LocalDateTime.now());
        HttpSession session = req.getSession(false);
        if(session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);

            if (req.getParameter("action").equals("submit-ticket")) {
                if(String.valueOf(ticket.getAmount()).equals("") || ticket.getAmount() <= 0 ){
                    resp.getWriter().write("Amount of ticket cannot be empty");
                    resp.setStatus(400);
                } else if (ticket.getReason().equals("")) {
                    HashMap<String, Object> errorMessage = new HashMap<>();
                    errorMessage.put("Status code", 400);
                    errorMessage.put("Message", "Ticket cannot have a null value for Description");
                    errorMessage.put("Timestamp", LocalDateTime.now().toString());
                    resp.getWriter().write(mapper.writeValueAsString(errorMessage));
                    resp.setContentType("application/json");
                    resp.setStatus(400);
                } else{
                    Ticket tick = tsa.create(ticket.getAmount(), ticket.getReason(), loggedInEmployee);
                    String payload = mapper.writeValueAsString(tick);
                    resp.setContentType("application/json");
                    resp.getWriter().write(payload);
                    resp.setStatus(201);
                }


            }
        } else{
            resp.getWriter().write("You must be logged in to create a ticket");
            resp.setStatus(403);
        }

    }

}
