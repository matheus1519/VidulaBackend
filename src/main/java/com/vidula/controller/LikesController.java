/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.controller;

import com.vidula.model.Likes;
import com.vidula.repository.LikesRepository;
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
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    private LikesRepository likes;

    @GetMapping()
    public List<Likes> listAll() {
        return likes.findAll();
    }

    @PostMapping
    public Likes post(@RequestBody Likes like) {
        return likes.save(like);
    }

}
