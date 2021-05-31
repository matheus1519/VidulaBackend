/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.DTO;

import java.util.List;

/**
 *
 * @author 1519m
 */
public class DisciplineDTO {
    private Long id;
    private String name;
    private String description;
    private List<SubjectExpandedDTO> subjects;

    public DisciplineDTO(Long id, String name, String description, List<SubjectExpandedDTO> subjects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subjects = subjects;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubjectExpandedDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectExpandedDTO> subjects) {
        this.subjects = subjects;
    }
    
}