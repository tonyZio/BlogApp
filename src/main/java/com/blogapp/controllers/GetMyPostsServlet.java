package com.blogapp.controllers;

import com.blogapp.dao.ComentarioDAO;
import com.blogapp.dao.EtiquetaDAO;
import com.blogapp.dao.PostDAO;
import com.blogapp.dto.ComentarioDTO;
import com.blogapp.dto.EtiquetaDTO;
import com.blogapp.dto.PostDTO;
import com.blogapp.entities.Comentario;
import com.blogapp.entities.Etiqueta;
import com.blogapp.entities.Post;
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

@WebServlet(name = "GetMyPosts", urlPatterns = {"/myposts"})
public class GetMyPostsServlet extends HttpServlet {
    
    PostDAO postDAO = new PostDAO();
    EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
    ComentarioDAO comentarioDAO = new ComentarioDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Post> posts = postDAO.consultar();
            ArrayList<Etiqueta> etiquetas = etiquetaDAO.consultar();
            ArrayList<Comentario> comentarios = comentarioDAO.consultar();
            ArrayList<PostDTO> userPosts = new ArrayList<>();
            
            String username = request.getParameter("username");
            
            for (Post post : posts) {
                if (post.getBlog().getBlogger().getNickname().equals(username)) {
                    PostDTO postDto = new PostDTO(post);
                    
                    ArrayList<EtiquetaDTO> postsEtiquetas = new ArrayList<>();
                    ArrayList<ComentarioDTO> postsComentarios = new ArrayList<>();
                    
                    for (Etiqueta etiqueta : etiquetas) {
                        if (etiqueta.getPost().getId().equals(post.getId())) {
                            postsEtiquetas.add(new EtiquetaDTO(etiqueta));
                        }
                    }
                    
                    for (Comentario comentario : comentarios) {
                        if (comentario.getPost().getId().equals(post.getId())) {
                            postsComentarios.add(new ComentarioDTO(comentario));
                        }
                    }
                    
                    postDto.setEtiquetasDTO(postsEtiquetas);
                    postDto.setComentarios(postsComentarios);
                    userPosts.add(postDto);
                }
            }
            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(userPosts));
            out.flush();
        } catch (Exception ex) {
            Logger.getLogger(GetBlogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
