package com.vidula.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;
   
    private String url;
    
    private Video proximo;
    
    private Video detalhe;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video getProximo() {
        return proximo;
    }

    public void setProximo(Video proximo) {
        this.proximo = proximo;
    }

    public Video getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(Video detalhe) {
        this.detalhe = detalhe;
    }

    
}