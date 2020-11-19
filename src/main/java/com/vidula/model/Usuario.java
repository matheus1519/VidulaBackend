package com.vidula.model;

import com.vidula.repository.PermissaoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String senha;
    
    @Column()
    private int levelAccess;

   

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permissao> permissoes;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @PrePersist
    public void addInitialPermission() {
        this.levelAccess = 1;
    }
    
     public int getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(int levelAccess) {
        this.levelAccess = levelAccess;
    }

    public void cryptoPass() {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        setSenha(bc.encode(getSenha()));
    }

    public boolean matchPass(String senha) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        return bc.matches(senha, getSenha());
    }

}
