package com.blogapp.test;

import com.blogapp.dao.BlogDAO;
import com.blogapp.dao.BloggerDAO;
import com.blogapp.dao.ComentarioBloggerDAO;
import com.blogapp.dao.ComentarioDAO;
import com.blogapp.dao.ComentarioUsuarioDAO;
import com.blogapp.dao.EtiquetaDAO;
import com.blogapp.dao.PostDAO;
import com.blogapp.entities.Blogger;
import com.blogapp.entities.Comentario;
import com.blogapp.entities.ComentarioBlogger;
import com.blogapp.entities.ComentarioUsuario;
import com.blogapp.entities.Post;
import java.time.Instant;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws Exception {
        BloggerDAO bdao = new BloggerDAO();
        BlogDAO bgdao = new BlogDAO();
        ComentarioBloggerDAO cbdao = new ComentarioBloggerDAO();
        ComentarioUsuarioDAO cudao = new ComentarioUsuarioDAO();
        ComentarioDAO cdao = new ComentarioDAO();
        EtiquetaDAO edao = new EtiquetaDAO();
        PostDAO pdao = new PostDAO();

        // Blogger test
//         Insertar
//      Blogger blogger = new Blogger("annd@gmail.com", "123456", "anndfelix");
//        bdao.insertar(blogger);
////
//        // Consultar por id
        Blogger blogger = bdao.consultarID(51);
        // Consultar blogs
//         List<Blog> blogs = bgdao.consultar() ;
//         System.out.println( blogs);
        // Blog test
        // Insertar
//        Blog blog = new Blog("Pinguinos", blogger, Date.from(Instant.now()));
//        Blog blog1 = new Blog("Delfines", blogger, Date.from(Instant.now()));
//        Blog blog2 = new Blog("Tortugas", blogger, Date.from(Instant.now()));
//        bgdao.insertar(blog);
//        bgdao.insertar(blog1);
//        bgdao.insertar(blog2);
        // Consultar por id
        // Blog blog = bgdao.consultarID(1);
        //Post test
        // Post post = new Post("Primer post","Mi primer post!", Date.from(Instant.now()), "/photo/post.jpg", blog);
        // pdao.insertar(post);
//         Post post = pdao.consultarID(1);
        // Comentario Blogger test
        // ComentarioBlogger comentarioBlogger =  new ComentarioBlogger(blogger, "Mi primer comentario!", Date.from(Instant.now()), post);
        // cbdao.insertar(comentarioBlogger);
        // Comentario test
        // Comentario Usuario test
        // Etiqueta test
//        Etiqueta etiqueta = new Etiqueta("Primer", post);
//        edao.insertar(etiqueta);
     //   Post post = pdao.consultarID(72);
    //    ComentarioUsuario comentarioUsuario = new ComentarioUsuario("anndz", "Si, son muy malos!", Date.from(Instant.now()), post);
      //  cudao.insertar(comentarioUsuario);
        

      //  ComentarioUsuario comentarioUsuario2 = new ComentarioUsuario("anndz", "Concuerdo!", Date.from(Instant.now()), post);
        System.out.println(cudao.consultar());
            System.out.println(cbdao.consultar());

//        
    }

}
