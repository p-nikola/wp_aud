package com.example.aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "bye",urlPatterns = "/bye")
public class ByeWorldServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer= resp.getWriter();
        writer.write("<h1>Bye</h1>");
        writer.flush();


    }

    @Override
    public void destroy() {
        System.out.println("Bye servlet is destroyed!");
    }
}
