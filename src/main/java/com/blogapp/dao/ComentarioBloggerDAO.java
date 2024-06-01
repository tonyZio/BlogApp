

package com.blogapp.dao;

import com.blogapp.entities.ComentarioBlogger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ComentarioBloggerDAO extends BaseDAO<ComentarioBlogger>{
    @Override
    public ArrayList<ComentarioBlogger> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT c FROM ComentarioBlogger c");
        List<ComentarioBlogger> comentarios = consulta.getResultList();
        return new ArrayList<>(comentarios);
    }

    @Override
    public void insertar(ComentarioBlogger comentarioBlogger) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(comentarioBlogger);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(ComentarioBlogger comentarioBlogger) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        ComentarioBlogger c = em.find(ComentarioBlogger.class, comentarioBlogger.getId());

        try {
            if (c != null) {
                c.setTexto(comentarioBlogger.getTexto());
                c.setFecha(comentarioBlogger.getFecha());
                em.persist(c);
            } else {
                throw new Exception("El comentario del blogger " + comentarioBlogger.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public ComentarioBlogger consultarID(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        ComentarioBlogger comentarioBlogger = em.find(ComentarioBlogger.class, id);

        try {
            if (comentarioBlogger != null) {
                return comentarioBlogger;
            } else {
                throw new Exception("El comentario del blogger " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return comentarioBlogger;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        ComentarioBlogger comentarioBlogger = em.find(ComentarioBlogger.class, id);

        try {

            if (comentarioBlogger != null) {
                em.remove(comentarioBlogger);
                System.out.println("Se elimino el comentario del blogger: " + comentarioBlogger.getTexto());
            } else {
                throw new Exception("El comentario del blogger " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }
}


