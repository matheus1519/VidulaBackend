/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.DTO;

import com.vidula.model.Video;
import java.util.List;

/**
 *
 * @author 1519m
 */
public class SubjectExpandedDTO {
    
    private Long id;
    private String nome;
    private String status;
    private Video inicio;
    private List<WatchDTO> watches;
    private List<CommentDTO> comments;
    private TeacherDTO teacher;

    public SubjectExpandedDTO(Long id, String nome, String status, Video inicio, List<WatchDTO> watches, List<CommentDTO> comments, TeacherDTO teacher) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.inicio = inicio;
        this.watches = watches;
        this.comments = comments;
        this.teacher = teacher;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Video getInicio() {
        return inicio;
    }

    public void setInicio(Video inicio) {
        this.inicio = inicio;
    }

    public List<WatchDTO> getWatches() {
        return watches;
    }

    public void setWatches(List<WatchDTO> watches) {
        this.watches = watches;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }
    
    
    
}
