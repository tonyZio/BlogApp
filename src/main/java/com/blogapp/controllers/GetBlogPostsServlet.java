package com.blogapp.controllers;

import com.blogapp.dao.BlogDAO;
import com.blogapp.dao.ComentarioBloggerDAO;
import com.blogapp.dao.ComentarioDAO;
import com.blogapp.dao.ComentarioUsuarioDAO;
import com.blogapp.dao.EtiquetaDAO;
import com.blogapp.dao.PostDAO;
import com.blogapp.dto.BlogDTO;
import com.blogapp.dto.ComentarioDTO;
import com.blogapp.dto.EtiquetaDTO;
import com.blogapp.dto.PostDTO;
import com.blogapp.entities.Blog;
import com.blogapp.entities.Comentario;
import com.blogapp.entities.ComentarioBlogger;
import com.blogapp.entities.ComentarioUsuario;
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

@WebServlet(name = "GetBlogPostsServlet", urlPatterns = {"/blog"})
public class GetBlogPostsServlet extends HttpServlet {

    BlogDAO blogDAO = new BlogDAO();
    PostDAO postDAO = new PostDAO();
    EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
    ComentarioDAO comentarioDAO = new ComentarioDAO();
    ComentarioBloggerDAO comentarioBloggerDAO = new ComentarioBloggerDAO();
    ComentarioUsuarioDAO comentarioUsuarioDAO = new ComentarioUsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String titulo_blog = request.getParameter("blog");

            ArrayList<Blog> blogs = blogDAO.consultar();
            ArrayList<Post> posts = postDAO.consultar();
            ArrayList<Etiqueta> etiquetas = etiquetaDAO.consultar();
            ArrayList<Comentario> comentarios = comentarioDAO.consultar();
            ArrayList<ComentarioBlogger> comentariosBlogger = comentarioBloggerDAO.consultar();
            ArrayList<ComentarioUsuario> comentariosUsuario = comentarioUsuarioDAO.consultar();

            ArrayList<PostDTO> blogPosts = new ArrayList<>();
            BlogDTO blogDto = null;
            for (Blog blog : blogs) {
                if (blog.getTitulo().equals(titulo_blog)) {
                    for (Post post : posts) {
                        if (post.getBlog().getId().equals(blog.getId())) {
                            PostDTO postDto = new PostDTO(post);
                            postDto.setUriImagen(post.getUriImagen());

                            ArrayList<EtiquetaDTO> postsEtiquetas = new ArrayList<>();
                            ArrayList<ComentarioDTO> postsComentarios = new ArrayList<>();
                            for (Etiqueta etiqueta : etiquetas) {
                                if (etiqueta.getPost().getId().equals(post.getId())) {
                                    postsEtiquetas.add(new EtiquetaDTO(etiqueta));
                                }
                            }

                            for (Comentario comentario : comentarios) {
                                if (comentario.getPost().getId().equals(post.getId())) {
                                    for (ComentarioBlogger comentarioBlogger : comentariosBlogger) {
                                        if (comentarioBlogger.getId().equals(comentario.getId())) {
                                            postsComentarios.add(new ComentarioDTO(comentario, comentarioBlogger.getBlogger().getNickname()));
                                        }
                                    }
                                    for (ComentarioUsuario comentarioUsuario : comentariosUsuario) {
                                        if (comentarioUsuario.getId().equals(comentario.getId())) {
                                            postsComentarios.add(new ComentarioDTO(comentario, comentarioUsuario.getNombreUsuario()));
                                        }
                                    }
                                }
                            }

                            postDto.setEtiquetasDTO(postsEtiquetas);
                            postDto.setComentarios(postsComentarios);
                            blogPosts.add(postDto);
                        }
                    }

                    blogDto = new BlogDTO(blog, blogPosts);
                }
            }

            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.write(gson.toJson(blogDto));
            out.flush();
        } catch (Exception ex) {
            Logger.getLogger(GetBlogPostsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
