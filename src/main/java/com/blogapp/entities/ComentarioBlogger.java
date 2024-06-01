
package com.blogapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comentario_blogger")
public class ComentarioBlogger extends Comentario implements Serializable{

    public ComentarioBlogger() {
    }

    public ComentarioBlogger(Blogger blogger, String texto, Date fecha, Post post) {
        super(texto, fecha, post);
        this.blogger = blogger;
    }

    @ManyToOne
    @JoinColumn(name = "id_blogger", nullable = false)
    private Blogger blogger;

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }

    @Override
    public String toString() {
        return "ComentarioBlogger{" + "blogger=" + blogger + '}';
    }



}