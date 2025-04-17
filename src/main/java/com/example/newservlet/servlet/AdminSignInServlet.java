package com.example.newservlet.servlet;
import com.example.newservlet.dto.request.SignInRequest;
import com.example.newservlet.dto.response.AuthResponse;
import com.example.newservlet.service.ReaderService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/signIn")
public class AdminSignInServlet extends HttpServlet {

    private ReaderService readerService;

    private String AUTHORIZATION;
    private String IS_ADMIN;
    private String SECRET_KEY;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        readerService = (ReaderService) context.getAttribute("readerService");
        AUTHORIZATION = (String) context.getAttribute("AUTHORIZATION");
        IS_ADMIN = (String) context.getAttribute("IS_ADMIN");
        SECRET_KEY = (String) context.getAttribute("SECRET_KEY");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../jsp/adminSignIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInRequest signInAdminRequest = SignInRequest.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        if (req.getParameter("codeword").equals(SECRET_KEY)) {
            AuthResponse authResponse = readerService.signIn(signInAdminRequest);
            authResponse = readerService.checkAdmin(authResponse.getReader());

            if(authResponse.getStatus() == 0) {
                HttpSession session = req.getSession(true);
                session.setAttribute(AUTHORIZATION, true);
                session.setAttribute(IS_ADMIN, true);
                session.setAttribute("user", authResponse.getReader());
                resp.sendRedirect("/admin/main");
            } else {
                resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
            }
        } else {
            resp.sendRedirect("error?err=" + "Invalid codeword");
        }
    }
}
