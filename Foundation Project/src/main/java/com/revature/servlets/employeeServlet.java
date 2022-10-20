package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;
import com.revature.model.NewApp;
import com.revature.model.Ticket;
import com.revature.service.EmployeeServiceAPI;
import com.revature.service.TicketServiceAPI;
import com.revature.service.employeeService;
import com.revature.service.ticketService;
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


    EmployeeServiceAPI es = new EmployeeServiceAPI();

    TicketServiceAPI tsa = new TicketServiceAPI();





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

}
