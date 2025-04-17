package com.example.newservlet.servlet;

import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.dto.response.ReaderDataResponse;
import com.example.newservlet.model.OrdersEntity;
import com.example.newservlet.service.BookService;
import com.example.newservlet.service.OrdersService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

    @WebServlet("/orders")
    public class OrdersServlet extends HttpServlet {

        private OrdersService ordersService;
        private BookService bookService;

        @Override
        public void init() throws ServletException {
            ServletContext servletContext = getServletContext();
            ordersService = (OrdersService) servletContext.getAttribute("ordersService");
            bookService = (BookService) servletContext.getAttribute("bookService");
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession(true);
            ReaderDataResponse reader = (ReaderDataResponse) session.getAttribute("reader");

            List<OrdersEntity> ordersEntityList = ordersService.getOrdersByReaderId(reader.getId());
            ListBooksResponse listBooksResponse = bookService.getAllBooks(reader.getId());

            session.setAttribute("orders", ordersEntityList);
            session.setAttribute("books", listBooksResponse);

            req.getRequestDispatcher("/jsp/orders.jsp").forward(req, resp);
        }
    }