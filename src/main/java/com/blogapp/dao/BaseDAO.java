
package com.blogapp.dao;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseDAO<T> {

    public EntityManager generarConexion() throws Exception {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("blogPU");
            EntityManager em = emf.createEntityManager();
            return em;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            throw new Exception(ex.getMessage(), ex);
        }
    }

    public abstract ArrayList<T> consultar() throws Exception;

    public abstract void insertar(T entidad) throws Exception;

    public abstract void actualizar(T entidad) throws Exception;

    public abstract T consultarID(Integer id) throws Exception;

    public abstract void eliminar(Integer id) throws Exception;

}