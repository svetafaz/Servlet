package com.example.newservlet.servlet;
import com.example.newservlet.dto.request.SignUpRequest;
import com.example.newservlet.dto.response.AuthResponse;
import com.example.newservlet.service.ReaderService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.newservlet.model.ReaderEntity.READER_ROLE;
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private ReaderService readerService;
    @Override
    public void init()throws ServletException{
        ServletContext context=getServletContext();
        readerService = (ReaderService) context.getAttribute("readerService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
    }
      @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
            SignUpRequest  signUpRequest = SignUpRequest.builder()
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .username(req.getParameter("username"))
                    .role(READER_ROLE)
                    .build();
            AuthResponse authResponse = readerService.signUp(signUpRequest);
            if (authResponse.getStatus() == 0) {
                resp.sendRedirect("/signIn");
            } else {
                resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
            }
        }
    }


