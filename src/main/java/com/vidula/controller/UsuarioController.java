package com.vidula.controller;

import com.vidula.model.Usuario;
import com.vidula.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarios;

    @GetMapping()
    public List<Usuario> listar() {
        return usuarios.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> listarUm(@PathVariable Long id) {
        return usuarios.findById(id);
    }

    @PutMapping("/{id}")
    public boolean atualizar(@PathVariable Long id, @RequestBody Usuario u) {
        if (u.getId() == id) {
            usuarios.save(u);
            return true;
        }
        return false;
    }

    @PostMapping
    public boolean cadastrar(@RequestBody Usuario u) {
        u.cryptoPass();
        usuarios.save(u);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean apagar(@PathVariable Long id) {
        if (usuarios.findById(id).equals(null)) {
            usuarios.deleteById(id);
            return true;
        }

        return false;
    }

    @GetMapping("/existe/{email}")
    public ResponseEntity<Boolean> buscaUsuario(@PathVariable String email) {
        if(email.isEmpty()){
            return ResponseEntity.ok(false);
        }
        Optional<Usuario> user = usuarios.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }
    
    @PostMapping("/autenticar")
    public boolean autenticar(@RequestBody Usuario u){
        Optional<Usuario> uDb = usuarios.findByEmail(u.getEmail());
        if (uDb.isPresent()){
            if(uDb.get().matchPass(u.getSenha())){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    

}
