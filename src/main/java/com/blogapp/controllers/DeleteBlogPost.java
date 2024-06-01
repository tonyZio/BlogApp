package com.blogapp.controllers;

import com.blogapp.dao.BlogDAO;
import com.blogapp.dao.ComentarioBloggerDAO;
import com.blogapp.dao.ComentarioDAO;
import com.blogapp.dao.ComentarioUsuarioDAO;
import com.blogapp.dao.EtiquetaDAO;
import com.blogapp.dao.PostDAO;
import com.blogapp.dto.PostDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteBlogPost", urlPatterns = {"/deleteBlogPost"})
public class DeleteBlogPost extends HttpServlet {
    
    PostDAO postDAO = new PostDAO();
    BlogDAO blogDAO = new BlogDAO();
    EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
    ComentarioDAO comentarioDAO = new ComentarioDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        
        PostDTO postDTO = gson.fromJson(json, PostDTO.class);
        
        try {
            
            postDAO.eliminar(postDTO.getId());
            
            for (Integer id_etiqueta : postDTO.getId_etiquetas()) {
                etiquetaDAO.eliminar(id_etiqueta);
            }
            
            for (Integer id_comentario : postDTO.getId_comentarios()) {
                comentarioDAO.eliminar(id_comentario);
            }
            
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.write("Se elimino el post con exito!");
            }
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
