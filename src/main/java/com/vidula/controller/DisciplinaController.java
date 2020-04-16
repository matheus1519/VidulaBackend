package com.vidula.controller;

import com.vidula.model.Disciplina;
import com.vidula.repository.DisciplinaRepository;
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
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinas;

    @GetMapping()
    public List<Disciplina> listar() {
        return disciplinas.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Disciplina> listarUm(@PathVariable Long id) {
        return disciplinas.findById(id);
    }

    @PutMapping("/{id}")
    public boolean put(@PathVariable Long id, @RequestBody Disciplina v) {
        if (disciplinas.findById(id) != null) {
            disciplinas.save(v);
            return true;
        }
        return false;
    }

    @PostMapping
    public Disciplina post(@RequestBody Disciplina v) {
        Disciplina disc = disciplinas.save(v);
        return disc;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        if (disciplinas.findById(id) != null) {
            disciplinas.deleteById(id);
            return true;
        }

        return false;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
