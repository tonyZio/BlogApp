package com.blogapp.dto;

import com.blogapp.entities.Post;
import java.util.Date;
import java.util.List;

public class PostDTO {

    private Integer id;
    private String titulo;
    private String cuerpo;
    private Date fecha;
    private List<String> etiquetas;
    private List<EtiquetaDTO> etiquetasDTO;
    private List<Integer> id_etiquetas;
    private List<ComentarioDTO> comentarios;
    private List<Integer> id_comentarios;
    private BlogDTO blogDTO;
    private int id_blog;
    private String uriImagen;

    public PostDTO() {
    }

    public PostDTO(List<Integer> id_etiquetas, String titulo, String cuerpo, String uriImagen, List<String> etiquetas, int id_blog) {
        this.id_etiquetas = id_etiquetas;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.uriImagen = uriImagen;
        this.etiquetas = etiquetas;
        this.id_blog = id_blog;
    }

    public PostDTO(Integer id, List<Integer> id_comentarios, List<Integer> id_etiquetas) {
        this.id = id;
        this.id_etiquetas = id_etiquetas;
        this.id_comentarios = id_comentarios;
    }

    public PostDTO(Integer id, String titulo, String cuerpo, String uriImagen, List<String> etiquetas, int id_blog) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.uriImagen = uriImagen;
        this.etiquetas = etiquetas;
        this.id_blog = id_blog;
    }

    public PostDTO(String titulo, String cuerpo, String uriImagen, List<String> etiquetas, int id_blog) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.uriImagen = uriImagen;
        this.etiquetas = etiquetas;
        this.id_blog = id_blog;
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.titulo = post.getTitulo();
        this.cuerpo = post.getCuerpo();
        this.fecha = post.getFecha();
        this.blogDTO = new BlogDTO(post.getBlog());
        this.uriImagen = post.getUriImagen();
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

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public BlogDTO getBlog() {
        return blogDTO;
    }

    public void setBlog(BlogDTO blog) {
        this.blogDTO = blog;
    }

    public void setUriImagen(String uriImagen) {
        this.uriImagen = uriImagen;
    }

    public String getUriImagen() {
        return uriImagen;
    }

    public BlogDTO getBlogDTO() {
        return blogDTO;
    }

    public int getId_blog() {
        return id_blog;
    }

    public void setBlogDTO(BlogDTO blogDTO) {
        this.blogDTO = blogDTO;
    }

    public List<EtiquetaDTO> getEtiquetasDTO() {
        return etiquetasDTO;
    }

    public void setEtiquetasDTO(List<EtiquetaDTO> etiquetasDTO) {
        this.etiquetasDTO = etiquetasDTO;
    }

    public List<Integer> getId_etiquetas() {
        return id_etiquetas;
    }

    public void setId_etiquetas(List<Integer> id_etiquetas) {
        this.id_etiquetas = id_etiquetas;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public void setId_comentarios(List<Integer> id_comentarios) {
        this.id_comentarios = id_comentarios;
    }

    public List<Integer> getId_comentarios() {
        return id_comentarios;
    }

    @Override
    public String toString() {
        return "PostDTO{" + "id=" + id + ", titulo=" + titulo + ", cuerpo=" + cuerpo + ", fecha=" + fecha + ", uriImagen=" + uriImagen + '}';
    }

}
