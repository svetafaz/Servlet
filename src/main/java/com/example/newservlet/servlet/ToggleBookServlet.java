package com.example.newservlet.servlet;
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

@WebServlet("/toggleBook")
public class ToggleBookServlet extends HttpServlet {

    private SelectedService selectedService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        selectedService = (SelectedService) context.getAttribute("selectedService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long bookId = Long.parseLong(req.getParameter("bookId"));
        boolean isSelected = Boolean.parseBoolean(req.getParameter("isSelected"));

        HttpSession session = req.getSession(true);
        ReaderDataResponse reader = (ReaderDataResponse) session.getAttribute("reader");

        Long readerId = reader.getId();

        if (!isSelected) {
            selectedService.addSelected(readerId, bookId);
        } else {
            selectedService.deleteSelected(readerId, bookId);
        }

        resp.sendRedirect("/books");
    }
}