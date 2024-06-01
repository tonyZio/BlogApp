package com.blogapp.dto;

import com.blogapp.entities.Comentario;
import java.util.Date;

public class ComentarioDTO {

    private Integer id;
    private String autor;
    private String contenido;
    private Date fecha;
    private PostDTO postDTO;
    private Integer id_post;

    public ComentarioDTO(String autor, String contenido, Integer id_post) {
        this.autor = autor;
        this.contenido = contenido;
        this.id_post = id_post;
    }

    public ComentarioDTO(Comentario comentario, String autor) {
        this.id = comentario.getId();
        this.contenido = comentario.getTexto();
        this.fecha = comentario.getFecha();
        this.postDTO = new PostDTO(comentario.getPost());
        this.autor = autor;
    }

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.contenido = comentario.getTexto();
        this.fecha = comentario.getFecha();
        this.postDTO = new PostDTO(comentario.getPost());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

}
