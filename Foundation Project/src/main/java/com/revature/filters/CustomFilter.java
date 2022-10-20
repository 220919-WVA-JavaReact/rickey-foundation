package com.revature.filters;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[LOG] - CustomFilter was initialized!");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("[LOG] - CustomFilter intercepts web requests at " + LocalDateTime.now());
        req.setAttribute("was-filtered", true);
        // res.setHeader("example-response-header", "some-example-value");
        chain.doFilter(req, res);
    }

}
