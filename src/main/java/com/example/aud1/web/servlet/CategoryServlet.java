package com.example.aud1.web.servlet;

import com.example.aud1.model.Category;
import com.example.aud1.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "category-servlet",urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {



    private final CategoryService categoryService;
    public CategoryServlet(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String ipaddress=req.getRemoteAddr();
        String clientAgent=req.getHeader("User-Agent");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3>User Info</h3>");
        writer.format("IP Address: %s",ipaddress);
        writer.format("<br>");
        writer.format("Client Agent %s",clientAgent);
        writer.println("<h3>Category List</h3>");
        writer.println("<ul>");
        categoryService.listCategories().forEach(i->{
            writer.format("<li>%s (%s)</li>",i.getName(),i.getDescription());

        });
        writer.println("</ul>");

        writer.format("<form method='POST' action='/servlet/category'>\n" +
                "<label for='name'>Name:</label>\n" +
                "<input id='name' type='text' name='name'/>\n" +
                "<label for='desc'>Description:</label>\n" +
                "<input id='name' type='text' name='desc'/>\n" +
                "<input type='submit' value='Submit'/>\n" +
                "</form>");


        writer.println("</body>");
        writer.println("</html>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName=req.getParameter("name");
        String categoryDesc=req.getParameter("desc");
        categoryService.create(categoryName,categoryDesc);

        resp.sendRedirect("/servlet/category");


    }


}
