
package com.blogapp.dao;

import com.blogapp.entities.Comentario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ComentarioDAO extends BaseDAO<Comentario> {

    @Override
    public ArrayList<Comentario> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT c FROM Comentario c");
        List<Comentario> comentarios = consulta.getResultList();
        return new ArrayList<>(comentarios);
    }

    @Override
    public void insertar(Comentario comentario) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Comentario comentario) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Comentario c = em.find(Comentario.class, comentario.getId());

        try {
            if (c != null) {
                c.setTexto(comentario.getTexto());
                c.setFecha(comentario.getFecha());
                em.persist(c);
            } else {
                throw new Exception("El comentario " + comentario.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public Comentario consultarID(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        Comentario comentario = em.find(Comentario.class, id);

        try {
            if (comentario != null) {
                return comentario;
            } else {
                throw new Exception("El comentario " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return comentario;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Comentario comentario = em.find(Comentario.class, id);

        try {

            if (comentario != null) {
                em.remove(comentario);
                System.out.println("Se elimino el comentario: " + comentario.getTexto());
            } else {
                throw new Exception("El comentario " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }
}
