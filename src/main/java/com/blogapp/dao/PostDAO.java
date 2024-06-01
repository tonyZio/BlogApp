
package com.blogapp.dao;

import com.blogapp.entities.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PostDAO extends BaseDAO<Post>{
    @Override
    public ArrayList<Post> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT p FROM Post p");
        List<Post> posts = consulta.getResultList();
        return new ArrayList<>(posts);
    }

    @Override
    public void insertar(Post post) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(post);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Post post) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Post p = em.find(Post.class, post.getId());

        try {
            if (p != null) {
                p.setTitulo(post.getTitulo());
                p.setCuerpo(post.getCuerpo());
                p.setFecha(post.getFecha());
                p.setUriImagen(post.getUriImagen());

                em.persist(p);
            } else {
                throw new Exception("El post " + post.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public Post consultarID(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        Post post = em.find(Post.class, id);

        try {
            if (post != null) {
                return post;
            } else {
                throw new Exception("El post " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return post;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Post post = em.find(Post.class, id);

        try {

            if (post != null) {
                em.remove(post);
                System.out.println("Se elimino el post: " + post.getTitulo());
            } else {
                throw new Exception("El post " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }
}

