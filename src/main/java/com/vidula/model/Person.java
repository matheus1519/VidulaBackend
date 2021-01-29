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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    private String gender;

    private String birth;

    private String cpf;

    private int levelAccess;

    private String avatarUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permissao> permits;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Permissao> getPermits() {
        return permits;
    }

    public void setPermits(List<Permissao> permits) {
        this.permits = permits;
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
        setPassword(bc.encode(getPassword()));
    }

    public boolean matchPass(String password) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        return bc.matches(password, getPassword());
    }

}
