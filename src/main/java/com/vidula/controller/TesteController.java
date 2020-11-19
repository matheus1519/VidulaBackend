package com.vidula.controller;

import com.vidula.model.Teste;
import com.vidula.repository.TesteRepository;
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
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    private TesteRepository testes;

    @GetMapping()
    public List<Teste> listar() {
        return testes.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Teste> listarUm(@PathVariable Long id) {
        return testes.findById(id);
    }

    @PutMapping("/{id}")
    public Teste put(@PathVariable Long id, @RequestBody Teste t) {
        if (testes.findById(id) != null) {
            return testes.save(t);
        }
        return null;
    }

    @PostMapping
    public Teste post(@RequestBody Teste v) {
        return testes.save(v);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        if (testes.findById(id) != null) {
            testes.deleteById(id);
            return true;
        }

        return false;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
