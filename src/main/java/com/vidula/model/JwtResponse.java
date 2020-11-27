package com.vidula.model;

import com.vidula.DTO.UsuarioDTO;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private UsuarioDTO user;

    public JwtResponse(String jwttoken, UsuarioDTO user) {
        this.jwttoken = jwttoken;
        this.user = user;

    }

    public String getToken() {
        return this.jwttoken;
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }

}
