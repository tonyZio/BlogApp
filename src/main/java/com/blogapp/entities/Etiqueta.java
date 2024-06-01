package com.blogapp.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "etiqueta")
public class Etiqueta implements Serializable {

    public Etiqueta() {
    }

    public Etiqueta(String textoEtiqueta, Post post) {
        this.textoEtiqueta = textoEtiqueta;
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private Integer id;

    @Column(name = "texto")
    private String textoEtiqueta;

    @ManyToOne
    @JoinColumn(name = "id_post", nullable = false)
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextoEtiqueta() {
        return textoEtiqueta;
    }

    public void setTextoEtiqueta(String textoEtiqueta) {
        this.textoEtiqueta = textoEtiqueta;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.textoEtiqueta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Etiqueta other = (Etiqueta) obj;
        if (!Objects.equals(this.textoEtiqueta, other.textoEtiqueta)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "id=" + id + ", textoEtiqueta=" + textoEtiqueta + '}';
    }

}
