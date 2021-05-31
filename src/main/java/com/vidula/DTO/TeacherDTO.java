/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.DTO;

import com.vidula.model.Person;
import javax.persistence.OneToOne;

/**
 *
 * @author 1519m
 */
public class TeacherDTO {
     private Long id;
    
    private String videoUrl;

    private String area;
    
    private String status; 
   
    private PersonDTO person;

    public TeacherDTO(Long id, String videoUrl, String area, String status, PersonDTO person) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.area = area;
        this.status = status;
        this.person = person;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }
    
    
}
