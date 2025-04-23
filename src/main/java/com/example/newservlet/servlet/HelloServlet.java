package com.example.newservlet.servlet;

import java.io.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    public void init() {
          message = "Hello World!";
    }
    protected  void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException {
     resp.setContentType("text/html");
             PrintWriter out = resp.getWriter();

        out.println("<h1>" + message + "</h1>");
               out.println("</body></html>");
    }
@Override
    public void destroy() {
        super.destroy();
    }
}