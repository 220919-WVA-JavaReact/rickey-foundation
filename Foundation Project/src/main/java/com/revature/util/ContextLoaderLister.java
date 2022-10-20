package com.revature.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ticketImplPostgres;
import com.revature.servlets.AuthServlet;
import com.revature.servlets.ManagerServlet;
import com.revature.servlets.employeeServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class ContextLoaderLister implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was initialized! " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        ticketImplPostgres tp = new ticketImplPostgres();
        employeeServlet employeeServlet = new employeeServlet();
        ManagerServlet managerServlet = new ManagerServlet(mapper, tp);
        AuthServlet authServlet = new AuthServlet(mapper);


        ServletContext context = sce.getServletContext();
        context.addServlet("employeeServlet", employeeServlet).addMapping("/login");
        context.addServlet("managerServlet", managerServlet).addMapping("/view-tickets");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed! " + LocalDateTime.now());
    }
}
