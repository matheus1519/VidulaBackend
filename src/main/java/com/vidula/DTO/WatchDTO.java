/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vidula.model.Assunto;
import com.vidula.model.Person;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author 1519m
 */
public class WatchDTO {
    private Long id;

    private String path;

    private PersonDTO person;

    private OnlyIdDTO subject;

    public WatchDTO(Long id, String path, PersonDTO person, OnlyIdDTO subject) {
        this.id = id;
        this.path = path;
        this.person = person;
        this.subject = subject;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OnlyIdDTO getSubject() {
        return subject;
    }

    public void setSubject(OnlyIdDTO subject) {
        this.subject = subject;
    }
    
    
}
