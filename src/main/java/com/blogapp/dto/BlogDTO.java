package com.blogapp.dto;

import com.blogapp.entities.Blog;
import java.util.Date;
import java.util.List;

public class BlogDTO {

    private Integer id;
    private String titulo;
    private BloggerDTO blogger;
    private Date fecha;
    private String imagen;
    private List<PostDTO> posts;

    public BlogDTO() {
    }

    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.titulo = blog.getTitulo();
        this.blogger = new BloggerDTO(blog.getBlogger());
        this.fecha = blog.getFecha();

    }

    public BlogDTO(Blog blog, List<PostDTO> posts) {
        this.id = blog.getId();
        this.titulo = blog.getTitulo();
        this.fecha = blog.getFecha();
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BloggerDTO getBlogger() {
        return blogger;
    }

    public void setBlogger(BloggerDTO blogger) {
        this.blogger = blogger;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "BlogDTO{" + "id=" + id + ", titulo=" + titulo + ", blogger=" + blogger + ", fecha=" + fecha + ", imagen=" + imagen + '}';
    }
}
