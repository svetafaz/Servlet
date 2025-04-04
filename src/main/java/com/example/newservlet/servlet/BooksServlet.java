package com.example.newservlet.servlet;

import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.dto.response.ListCategoriesResponse;
import com.example.newservlet.service.BookService;
import com.example.newservlet.service.CategoryService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
    private BookService bookService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        bookService = (BookService) servletContext.getAttribute("bookService");
        categoryService = (CategoryService) servletContext.getAttribute("categoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        ListBooksResponse listBooksResponse = bookService.getAllBooks();
        ListCategoriesResponse listCategoriesResponse = categoryService.getAllCategories();
        session.setAttribute("books", listBooksResponse);
        session.setAttribute("categories", listCategoriesResponse);
        req.getRequestDispatcher("jsp/books.jsp").forward(req, resp);
    }
}

