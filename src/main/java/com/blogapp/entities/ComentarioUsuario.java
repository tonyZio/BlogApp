
package com.blogapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comentario_usuario")
public class ComentarioUsuario extends Comentario implements Serializable {

    public ComentarioUsuario() {
    }

    public ComentarioUsuario(String nombreUsuario, String texto, Date fecha, Post post) {
        super(texto, fecha, post);
        this.nombreUsuario = nombreUsuario;
    }

    public ComentarioUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario
                = nombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nombreUsuario);
        return hash;
    }

    @Override
    public String toString() {
        return "ComentarioUsuario{" + "nombreUsuario=" + nombreUsuario + '}';
    }

}