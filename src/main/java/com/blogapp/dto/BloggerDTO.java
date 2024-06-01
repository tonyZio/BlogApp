package com.blogapp.dto;

import com.blogapp.entities.Blogger;

public class BloggerDTO {

    private Integer id;
    private String username;
    private String contraseña;

    public BloggerDTO() {
    }

    public BloggerDTO(Blogger blogger) {
        this.id = blogger.getId();
        this.username = blogger.getNickname();
    }

    public BloggerDTO(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "BloggerDTO{" + "id=" + id + ", username=" + username + '}';
    }
}
