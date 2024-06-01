package com.blogapp.controllers;

import com.blogapp.dao.BlogDAO;
import com.blogapp.dto.BlogDTO;
import com.blogapp.entities.Blog;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetMyBlogsServlet", urlPatterns = {"/myblogs"})
public class GetMyBlogsServlet extends HttpServlet {

    BlogDAO blogdao = new BlogDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Blog> blogs = blogdao.consultar();

            ArrayList<BlogDTO> userBlogs = new ArrayList<>();

            String username = request.getParameter("username");

            for (Blog blog : blogs) {
                if (blog.getBlogger().getNickname().equals(username)) {

                    BlogDTO blogDTO = new BlogDTO(blog);
                    
                    if (blog.getImagen() != null) {
                        String imageUrl = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(blog.getImagen());
                        blogDTO.setImagen(imageUrl);
                    }

                    userBlogs.add(blogDTO);
                }
            }

            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.write(gson.toJson(userBlogs));
            out.flush();
        } catch (Exception ex) {
            Logger.getLogger(GetBlogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
