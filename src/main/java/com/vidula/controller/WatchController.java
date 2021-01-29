/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.controller;

import com.vidula.model.Assunto;
import com.vidula.model.Watch;
import com.vidula.repository.WatchRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1519m
 */

@RestController
@RequestMapping("/watch")
public class WatchController {
    
    @Autowired
    private WatchRepository watches;
    
    @GetMapping()
    public List<Watch> listAll() {
        return watches.findAll();
    }
    
    @PostMapping
    public Watch post(@RequestBody Watch w) {
        return watches.save(w);
    }
    
}
