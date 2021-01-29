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
public class PersonDTO {
    private Long id;

    private String name;

    private String email;

    private int levelAccess;

    public PersonDTO(Long id, String name, String email, int levelAccess) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.levelAccess = levelAccess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
