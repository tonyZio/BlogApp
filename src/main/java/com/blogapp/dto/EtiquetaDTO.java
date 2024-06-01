package com.blogapp.dto;

import com.blogapp.entities.Etiqueta;

public class EtiquetaDTO {

    private Integer id;
    private String texto;
    private PostDTO postDTO;

    public EtiquetaDTO() {
    }

    public EtiquetaDTO(Etiqueta etiqueta) {
        this.id = etiqueta.getId();
        this.texto = etiqueta.getTextoEtiqueta();
        this.postDTO = new PostDTO(etiqueta.getPost());
    }

    public EtiquetaDTO(Integer id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    
    
}
