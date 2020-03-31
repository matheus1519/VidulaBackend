package com.vidula.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String nome;

    public JwtResponse(String jwttoken, String nome) {
        this.jwttoken = jwttoken;
        this.nome = nome;
        
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   
    
}
