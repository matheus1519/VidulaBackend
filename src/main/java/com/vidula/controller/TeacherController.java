/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.controller;

import com.vidula.model.Disciplina;
import com.vidula.model.Teacher;
import com.vidula.repository.TeacherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1519m
 */

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teachers;

    @GetMapping()
    public List<Teacher> listAll() {
        return teachers.findAll();
    }

    @PostMapping
    public Teacher post(@RequestBody Teacher teacher) {
        return teachers.save(teacher);
    }
    
    @PutMapping("/{id}")
    public Optional<Teacher> put(@PathVariable Long id, @RequestBody Teacher t) {
        Optional<Teacher> teacher = teachers.findById(id);
        if (teacher.get() != null) {
            teachers.save(t);
        }
        
        return teacher;
    }

}
