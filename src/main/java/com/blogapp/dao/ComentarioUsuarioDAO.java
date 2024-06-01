

package com.blogapp.dao;

import com.blogapp.entities.ComentarioUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ComentarioUsuarioDAO extends BaseDAO<ComentarioUsuario>{
    @Override
    public ArrayList<ComentarioUsuario> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT c FROM ComentarioUsuario c");
        List<ComentarioUsuario> comentarios = consulta.getResultList();
        return new ArrayList<>(comentarios);
    }

    @Override
    public void insertar(ComentarioUsuario comentarioUsuario) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(comentarioUsuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(ComentarioUsuario comentarioUsuario) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        ComentarioUsuario c = em.find(ComentarioUsuario.class, comentarioUsuario.getId());

        try {
            if (c != null) {
                c.setTexto(comentarioUsuario.getTexto());
                c.setFecha(comentarioUsuario.getFecha());
                em.persist(c);
            } else {
                throw new Exception("El comentario del usuario " + comentarioUsuario.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public ComentarioUsuario consultarID(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        ComentarioUsuario comentarioUsuario = em.find(ComentarioUsuario.class, id);

        try {
            if (comentarioUsuario != null) {
                return comentarioUsuario;
            } else {
                throw new Exception("El comentario del usuario " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return comentarioUsuario;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        ComentarioUsuario comentarioUsuario = em.find(ComentarioUsuario.class, id);

        try {

            if (comentarioUsuario != null) {
                em.remove(comentarioUsuario);
                System.out.println("Se elimino el comentario del usuario: " + comentarioUsuario.getTexto());
            } else {
                throw new Exception("El comentario del usuario " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }
}


