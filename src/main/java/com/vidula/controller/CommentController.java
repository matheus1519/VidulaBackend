/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.controller;

import com.vidula.model.Comment;
import com.vidula.repository.CommentRepository;
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
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository comments;

    @GetMapping()
    public List<Comment> listAll() {
        return comments.findAll();
    }

    @PostMapping
    public Comment post(@RequestBody Comment comment) {
        return comments.save(comment);
    }
    
    @PutMapping("/{id}")
    public Comment put(@PathVariable Long id, @RequestBody Comment comment) {
        Comment commentBefore = comments.findById(id).get();
        
        if(commentBefore != null){
            if(comment.getDoubt() != null){
                commentBefore.setDoubt(comment.getDoubt());
            }
            
            if(comment.getAnswer()!= null){
                commentBefore.setAnswer(comment.getAnswer());
            }
            
            comments.save(commentBefore);
        }
        
        return commentBefore;
    }

}
