package com.revature.servlets;

import com.revature.model.Employee;
import com.revature.service.ticketService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/tickets")
public class ticketServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    Employee employee = new Employee();

    ticketService ts = new ticketService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LOG - ticketServlet received a GET request at " + LocalDateTime.now());
        ts.getAllTickets(employee);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - ticketServlet recieved a request at " + LocalDateTime.now());


        //ts.create(employee);
    }

}
