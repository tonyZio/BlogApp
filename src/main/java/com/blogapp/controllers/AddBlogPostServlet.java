package com.blogapp.controllers;

import com.blogapp.dao.BlogDAO;
import com.blogapp.dao.EtiquetaDAO;
import com.blogapp.dao.PostDAO;
import com.blogapp.dto.PostDTO;
import com.blogapp.entities.Blog;
import com.blogapp.entities.Etiqueta;
import com.blogapp.entities.Post;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddBlogPost", urlPatterns = {"/addBlogPost"})
public class AddBlogPostServlet extends HttpServlet {

    PostDAO postDAO = new PostDAO();
    BlogDAO blogDAO = new BlogDAO();
    EtiquetaDAO etiquetaDAO = new EtiquetaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Gson gson = new Gson();

        PostDTO postDTO = gson.fromJson(json, PostDTO.class);

        try {
            Blog blog = blogDAO.consultarID(postDTO.getId_blog());
            Date fecha = Date.from(Instant.now());

            Post post = new Post(postDTO.getTitulo(), postDTO.getCuerpo(), fecha, postDTO.getUriImagen(), blog);

            postDAO.insertar(post);

            for (String etiquetaNombre : postDTO.getEtiquetas()) {
                Etiqueta etiqueta = new Etiqueta(etiquetaNombre, post);
                etiquetaDAO.insertar(etiqueta);
            }

            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.write("Se agrego el post con exito!");
            }
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
