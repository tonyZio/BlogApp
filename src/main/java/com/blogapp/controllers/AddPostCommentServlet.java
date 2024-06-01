package com.blogapp.controllers;

import com.blogapp.dao.BloggerDAO;
import com.blogapp.dao.ComentarioBloggerDAO;
import com.blogapp.dao.ComentarioUsuarioDAO;
import com.blogapp.dao.PostDAO;
import com.blogapp.dto.ComentarioDTO;
import com.blogapp.entities.Blog;
import com.blogapp.entities.Blogger;
import com.blogapp.entities.Comentario;
import com.blogapp.entities.ComentarioBlogger;
import com.blogapp.entities.ComentarioUsuario;
import com.blogapp.entities.Etiqueta;
import com.blogapp.entities.Post;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddPostCommentServlet", urlPatterns = {"/addPostComment"})
public class AddPostCommentServlet extends HttpServlet {

    PostDAO postDAO = new PostDAO();
    BloggerDAO bloggerDAO = new BloggerDAO();
    ComentarioBloggerDAO comentarioBloggerDAO = new ComentarioBloggerDAO();
    ComentarioUsuarioDAO comentarioUsuarioDAO = new ComentarioUsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Gson gson = new Gson();

        ComentarioDTO comentarioDto = gson.fromJson(json, ComentarioDTO.class);

        try {
            ArrayList<Blogger> bloggers = bloggerDAO.consultar();
            Post post = postDAO.consultarID(comentarioDto.getId_post());
            Date fecha = Date.from(Instant.now());

            boolean bloggerFound = false;

            for (Blogger blogger : bloggers) {
                if (blogger.getNickname().equals(comentarioDto.getAutor())) {
                    ComentarioBlogger comentarioBlogger = new ComentarioBlogger(blogger, comentarioDto.getContenido(), fecha, post);
                    comentarioBloggerDAO.insertar(comentarioBlogger);
                    bloggerFound = true;
                    break;
                }
            }

            if (!bloggerFound) {
                ComentarioUsuario comentarioUsuario = new ComentarioUsuario(comentarioDto.getAutor(), comentarioDto.getContenido(), fecha, post);
                comentarioUsuarioDAO.insertar(comentarioUsuario);
            }

            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.write("Se agrego el comentario con exito!");
            }
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }

    }

}
