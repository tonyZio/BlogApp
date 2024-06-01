
package com.blogapp.dao;

import com.blogapp.entities.Blogger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BloggerDAO extends BaseDAO<Blogger> {

    @Override
    public ArrayList<Blogger> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT b FROM Blogger b");
        List<Blogger> bloggers = consulta.getResultList();
        return new ArrayList<>(bloggers);
    }

    @Override
    public void insertar(Blogger blogger) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(blogger);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Blogger blogger) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Blogger b = em.find(Blogger.class, blogger.getId());

        try {
            if (b != null) {
                b.setCorreo(blogger.getCorreo());
                b.setContrasena(blogger.getContrasena());
                b.setNickname(blogger.getNickname());
                b.setBlogs(blogger.getBlogs());

                em.persist(b);

            } else {
                throw new Exception("El blogger " + blogger.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public Blogger consultarID(Integer  id) throws Exception {

        EntityManager em = this.generarConexion();
        Blogger blogger = em.find(Blogger.class, id);

        try {
            if (blogger != null) {
                return blogger;
            } else {
                throw new Exception("El blogger " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return blogger;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Blogger blogger = em.find(Blogger.class, id);

        try {

            if (blogger != null) {
                em.remove(blogger);
                System.out.println("Se elimino el blogger: " + blogger.getNickname());
            } else {
                throw new Exception("El blogger " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }

}