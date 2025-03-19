package com.example.newservlet.servlet;

import java.io.*;
import com.example.newservlet.service.impl.CalculatorImpl;
import com.example.newservlet.service.Calculator;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private Calculator calculator;

    public void init() {
        calculator = new CalculatorImpl();
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int a = 6;
        int b = 4;

//        int result = calculator.sum(a,b);
        int result = calculator.multiply(a,b);
//        int result = calculator.sub(a,b);
        // Hello
        PrintWriter out = response.getWriter();

        out.println("<h1>" + message + "</h1>");
        out.println("<h2> Ответ" + result + "</h2>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}