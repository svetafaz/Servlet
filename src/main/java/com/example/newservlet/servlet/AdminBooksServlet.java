package com.example.newservlet.servlet;
import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.dto.request.NewBookRequest;
import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.dto.response.ReaderDataResponse;
import com.example.newservlet.service.BookService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet5.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/books")
public class AdminBooksServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        bookService = (BookService) context.getAttribute("bookService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        ReaderDataResponse reader = (ReaderDataResponse) session.getAttribute("reader");

        ListBooksResponse listBooksResponse = bookService.getAllBooks(reader.getId());

        session.setAttribute("books", listBooksResponse);

        req.getRequestDispatcher("../jsp/adminBooks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!JakartaServletFileUpload.isMultipartContent(req)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form must contain enctype=multipart/form-data");
            return;
        }

        DiskFileItemFactory factory = DiskFileItemFactory.builder()
                .setCharset(StandardCharsets.UTF_8)
                .get();

        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        try {
            String name = "";
            String writer = "";
            double price = 0d;
            List<CategoryRequest> categories = new ArrayList<>();
            byte[] image = {};
            int quantity = 0;


            List<DiskFileItem> items = upload.parseRequest(req);

            for (DiskFileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "name" -> name = item.getString();
                        case "writer" -> writer = item.getString();
                        case "price" -> price = Double.parseDouble(item.getString());
                        case "quantity" -> quantity = Integer.parseInt(item.getString());
                        case "categories" -> categories.add(new CategoryRequest(item.getString()));
                    }
                } else {
                    if ("image".equals(item.getFieldName())) {
                        try (InputStream inputStream = item.getInputStream()) {
                            if (inputStream != null) {
                                image = IOUtils.toByteArray(inputStream);
                            } else {
                                image = new byte[]{1};
                            }

                        }
                    }
                }
            }

            NewBookRequest newBookRequest = NewBookRequest.builder()
                    .name(name)
                    .writer(writer)
                    .price(price)
                    .image(image)
                    .quantity(quantity)
                    .build();

            bookService.saveNewBook(newBookRequest, categories);

            resp.sendRedirect("/admin/books");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error processing upload: " + e.getMessage());
        }
    }
}