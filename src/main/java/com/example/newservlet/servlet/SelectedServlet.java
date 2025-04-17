package com.example.newservlet.servlet;

import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.dto.response.ReaderDataResponse;
import com.example.newservlet.service.SelectedService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/selected")
public class SelectedServlet extends HttpServlet {

    private SelectedService selectedService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        selectedService = (SelectedService) context.getAttribute("selectedService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        ReaderDataResponse reader = (ReaderDataResponse) session.getAttribute("reader");

        ListBooksResponse listBooksResponse = selectedService.getAllSelected(reader.getId());

        session.setAttribute("selected",listBooksResponse);

        req.getRequestDispatcher("jsp/selected.jsp").forward(req, resp);
    }
}