/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.DTO;

/**
 *
 * @author 1519m
 */
public class UsuarioDTO {
    private Long id;

    private String nome;

    private String email;

    private int levelAccess;

    public UsuarioDTO(Long id, String nome, String email, int levelAccess) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.levelAccess = levelAccess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(int levelAccess) {
        this.levelAccess = levelAccess;
    }
    
    
}
