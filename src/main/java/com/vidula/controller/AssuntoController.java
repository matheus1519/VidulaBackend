package com.vidula.controller;

import com.vidula.model.Assunto;
import com.vidula.repository.AssuntoRepository;
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
@RequestMapping("/assuntos")
public class AssuntoController {

    @Autowired
    private AssuntoRepository assuntos;

    @GetMapping()
    public List<Assunto> listar() {
        return assuntos.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Assunto> listarUm(@PathVariable Long id) {
        return assuntos.findById(id);
    }

    @PutMapping("/{id}")
    public boolean put(@PathVariable Long id, @RequestBody Assunto a) {
        if (assuntos.findById(id) != null) {
            assuntos.save(a);
            return true;
        }
        return false;
    }

    @PostMapping
    public boolean post(@RequestBody Assunto a) {
        assuntos.save(a);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        if (assuntos.findById(id) != null) {
            assuntos.deleteById(id);
            return true;
        }

        return false;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
