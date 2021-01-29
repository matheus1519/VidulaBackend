package com.vidula.model;

import com.vidula.DTO.PersonDTO;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private PersonDTO user;

    public JwtResponse(String jwttoken, PersonDTO user) {
        this.jwttoken = jwttoken;
        this.user = user;

    }

    public String getToken() {
        return this.jwttoken;
    }

    public PersonDTO getUser() {
        return user;
    }

    public void setUser(PersonDTO user) {
        this.user = user;
    }

}
