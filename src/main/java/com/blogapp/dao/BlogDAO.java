
package com.blogapp.dao;

import com.blogapp.entities.Blog;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BlogDAO extends BaseDAO<Blog>{
    @Override
    public ArrayList<Blog> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT b FROM Blog b");
        List<Blog> blogs = consulta.getResultList();
        return new ArrayList<>(blogs);
    }

    @Override
    public void insertar(Blog blog) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(blog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Blog blog) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Blog b = em.find(Blog.class, blog.getId());

        try {
            if (b != null) {
                b.setTitulo(blog.getTitulo());
                em.persist(b);
            } else {
                throw new Exception("El blog " + blog.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public Blog consultarID(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        Blog blog = em.find(Blog.class, id);

        try {
            if (blog != null) {
                return blog;
            } else {
                throw new Exception("El blog " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return blog;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Blog blog = em.find(Blog.class, id);

        try {

            if (blog != null) {
                em.remove(blog);
                System.out.println("Se elimino el blog: " + blog.getTitulo());
            } else {
                throw new Exception("El blog " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }
}
