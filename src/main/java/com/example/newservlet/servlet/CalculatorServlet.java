package com.example.newservlet.servlet;

import com.example.newservlet.service.Calculator;
import com.example.newservlet.service.impl.CalculatorImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
    private Calculator calculator;
@Override
    public void init() {
        calculator = new CalculatorImpl();
    }

    @Override
    protected  void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int a = Integer.parseInt(req.getParameter("value1"));
        int b = Integer.parseInt(req.getParameter("value2"));

        int result1 = calculator.sum(a,b);
        int result2 = calculator.multiply(a, b);
        int result3 = calculator.sub(a,b);

        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h2> Ответ" + result1 + "</h2>");
        out.println("</body></html>");
    }
    @Override
    public void destroy () {
        super.destroy();
    }
}

