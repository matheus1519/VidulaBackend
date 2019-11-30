package com.vidula.controller;

import com.vidula.model.Usuario;
import com.vidula.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro interno")
    public void handleError() {
    }

}
