package com.blogapp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "blogger")
public class Blogger implements Serializable {

    public Blogger() {
    }

    public Blogger(Integer id) {
        this.id = id;
    }

    public Blogger(String correo, String contrasena, String nickname) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nickname = nickname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_blogger")
    private Integer id;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL)
    private List<Blog> blogs;

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL)
    private List<ComentarioBlogger> comentariosBlogger;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<ComentarioBlogger> getComentariosBlogger() {
        return comentariosBlogger;
    }

    public void setComentariosBlogger(List<ComentarioBlogger> comentariosBlogger) {
        this.comentariosBlogger = comentariosBlogger;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.correo);
        hash = 59 * hash + Objects.hashCode(this.contrasena);
        hash = 59 * hash + Objects.hashCode(this.nickname);
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
        final Blogger other = (Blogger) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.contrasena, other.contrasena)) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Blogger{" + "id=" + id + ", correo=" + correo + ", contrasena=" + contrasena + ", nickname=" + nickname + '}';
    }

}
