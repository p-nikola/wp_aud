package com.example.aud1.web.servlet;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello", urlPatterns = {"/hello","/zdravo"},initParams = {@WebInitParam(name="a",value = "5")})
public class HelloWorldServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        PrintWriter writer = resp.getWriter();
        HttpSession session= req.getSession();
        ServletContext context = getServletContext();
        String test=  context.getInitParameter("a");


        writer.write("<html><head></head><body>Zdravo kako si " + name + " " + surname + " </body></html>");
        writer.flush();


    }

    @Override
    public void destroy() {
        System.out.println("Hello servlet is destroyed!");
    }
}
