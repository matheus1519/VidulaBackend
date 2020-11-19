package com.vidula.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teste implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String pergunta;

    @Column(length = 100)
    private String alternativa1;
    
    @Column(length = 100)
    private String alternativa2;
    
    @Column(length = 100)
    private String alternativa3;
    
    @Column(length = 100)
    private String alternativa4;
    
    @Column(length = 100)
    private String alternativa5;

    @Column(length = 100)
    private String alternativaCerta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public String getAlternativa3() {
        return alternativa3;
    }

    public void setAlternativa3(String alternativa3) {
        this.alternativa3 = alternativa3;
    }

    public String getAlternativa4() {
        return alternativa4;
    }

    public void setAlternativa4(String alternativa4) {
        this.alternativa4 = alternativa4;
    }

    public String getAlternativa5() {
        return alternativa5;
    }

    public void setAlternativa5(String alternativa5) {
        this.alternativa5 = alternativa5;
    }

    public String getAlternativaCerta() {
        return alternativaCerta;
    }

    public void setAlternativaCerta(String alternativaCerta) {
        this.alternativaCerta = alternativaCerta;
    }

    

}
