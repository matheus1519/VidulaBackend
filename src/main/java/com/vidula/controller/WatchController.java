/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidula.controller;

import com.vidula.DTO.PersonDTO;
import com.vidula.DTO.OnlyIdDTO;
import com.vidula.DTO.WatchDTO;
import com.vidula.model.Assunto;
import com.vidula.model.Watch;
import com.vidula.repository.WatchRepository;
import java.util.ArrayList;
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
    public List<WatchDTO> listAll() {
        List<WatchDTO> watchModified = new ArrayList<>();

        List<Watch> allWatches = watches.findAll();
        allWatches.forEach(watch -> {
            PersonDTO personModified = null;
            OnlyIdDTO subjectModified = null;

            if (watch.getPerson() != null) {
                personModified = new PersonDTO(watch.getPerson().getId(), watch.getPerson().getName(), watch.getPerson().getEmail(), watch.getPerson().getLevelAccess(), watch.getPerson().getAvatarUrl(), watch.getPerson().getGender());
            }

            if (watch.getSubject() != null) {
                subjectModified = new OnlyIdDTO(watch.getSubject().getId());
            }

            watchModified.add(new WatchDTO(watch.getId(), watch.getPath(), personModified, subjectModified));
        });

        return watchModified;
    }

    @PostMapping
    public Watch post(@RequestBody Watch w) {
        return watches.save(w);
    }

}
