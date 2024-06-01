

package com.blogapp.dao;

import com.blogapp.entities.Etiqueta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class EtiquetaDAO extends BaseDAO<Etiqueta>{
    @Override
    public ArrayList<Etiqueta> consultar() throws Exception {
        EntityManager em = this.generarConexion();
        Query consulta = em.createQuery("SELECT e FROM Etiqueta e");
        List<Etiqueta> etiquetas = consulta.getResultList();
        return new ArrayList<>(etiquetas);
    }

    @Override
    public void insertar(Etiqueta etiqueta) throws Exception {
        try {
            EntityManager em = this.generarConexion();
            em.getTransaction().begin();
            em.persist(etiqueta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Etiqueta etiqueta) throws Exception {
        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Etiqueta e = em.find(Etiqueta.class, etiqueta.getId());

        try {
            if (e != null) {
                e.setTextoEtiqueta(etiqueta.getTextoEtiqueta());
                em.persist(e);
            } else {
                throw new Exception("La etiqueta " + etiqueta.getId() + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        em.getTransaction().commit();
    }

    @Override
    public Etiqueta consultarID(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        Etiqueta etiqueta = em.find(Etiqueta.class, id);

        try {
            if (etiqueta != null) {
                return etiqueta;
            } else {
                throw new Exception("La etiqueta " + id + " no existe!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return etiqueta;
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        EntityManager em = this.generarConexion();
        em.getTransaction().begin();

        Etiqueta etiqueta = em.find(Etiqueta.class, id);

        try {

            if (etiqueta != null) {
                em.remove(etiqueta);
                System.out.println("Se elimino la etiqueta: " + etiqueta.getTextoEtiqueta());
            } else {
                throw new Exception("La etiqueta " + id + " no existe!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        em.getTransaction().commit();
    }
}



