package com.blogapp.controllers;

import com.blogapp.dao.BloggerDAO;
import com.blogapp.dto.BloggerDTO;
import com.blogapp.entities.Blogger;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    BloggerDAO bloggerdao = new BloggerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String correo = request.getParameter("email");
            String contraseña = request.getParameter("password");

            Blogger bloggerAuth = new Blogger();
            ArrayList<Blogger> bloggers = bloggerdao.consultar();

            if (!(correo.isBlank() || contraseña.isBlank() || correo == null || contraseña == null)) {
                for (Blogger blogger : bloggers) {
                    if (blogger.getCorreo().equals(correo) && blogger.getContrasena().equals(contraseña)) {
                        bloggerAuth = blogger;
                        break;
                    }
                }
            }

            BloggerDTO bloggerJSON = new BloggerDTO(bloggerAuth);
            HttpSession session = request.getSession();
            session.setAttribute("user", bloggerJSON);

            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.write(gson.toJson(bloggerJSON));
            out.flush();

        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
