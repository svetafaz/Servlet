package com.example.newservlet.servlet;
import com.example.newservlet.dto.response.ReaderDataResponse;
import com.example.newservlet.mapper.OrderMapper;
import com.example.newservlet.service.OrdersService;
import com.example.newservlet.dto.request.OrderRequest;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.newservlet.model.OrdersEntity.STATUS_PENDING;


@WebServlet("/saveOrder")
public class SaveOrdersServlet extends HttpServlet {

    private OrdersService ordersService;
    private OrderMapper orderMapper;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ordersService = (OrdersService) servletContext.getAttribute("ordersService");
        orderMapper = (OrderMapper) servletContext.getAttribute("orderMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("bookId"));

        HttpSession session = req.getSession(true);
        ReaderDataResponse reader = (ReaderDataResponse) session.getAttribute("reader");

        Long readerId = reader.getId();

        OrderRequest orderRequest = OrderRequest.builder()
                .readerId(readerId)
                .bookId(bookId)
                .statusCode(STATUS_PENDING)
                .orderDate(LocalDateTime.now())
                .build();

        ordersService.createOrder(orderMapper.toEntity(orderRequest));

        resp.sendRedirect("/orders");
    }
}

