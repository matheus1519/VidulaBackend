package com.vidula.controller;

import com.vidula.DTO.PersonDTO;
import com.vidula.model.Person;
import java.util.ArrayList;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import com.vidula.repository.PersonRepository;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/users")
public class PersonController {

    @Autowired
    private PersonRepository users;

    @GetMapping()
    public List<PersonDTO> listar() {
        List<Person> allUsers = users.findAll();
        List<PersonDTO> usersDTO = new ArrayList<>();

        allUsers.forEach((u) -> {
            usersDTO.add(new PersonDTO(u.getId(), u.getName(), u.getEmail(), u.getLevelAccess()));
        });

        return usersDTO;
    }

    @GetMapping("/{id}")
    public Optional<Person> listarUm(@PathVariable Long id) {
//        Optional<Person> user =  users.findById(id);
//        return new PersonDTO(user.get().getId(), user.get().getName(), user.get().getEmail(), user.get().getLevelAccess());
        return users.findById(id);
    }

    @PutMapping("/{id}")
    public Person atualizar(@PathVariable Long id, @RequestBody Person p) {
        if (p.getId() == id) {
            Person personBefore = users.findById(id).get();

            if (personBefore!=null) {
                if (p.getGender() != null) {
                    personBefore.setGender(p.getGender());
                }

                if (p.getBirth() != null) {
                    personBefore.setBirth(p.getBirth());
                }

                if (p.getCpf() != null) {
                    personBefore.setCpf(p.getCpf());
                }

                if (p.getAvatarUrl() != null) {
                    personBefore.setAvatarUrl(p.getAvatarUrl());
                }
                
                return users.save(personBefore);
                 
            }

        }
        return null;

    }

    @PostMapping
    public boolean cadastrar(@RequestBody Person u) {

        u.cryptoPass();
        users.save(u);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean apagar(@PathVariable Long id) {
        if (users.findById(id).equals(null)) {
            users.deleteById(id);
            return true;
        }

        return false;
    }

    @GetMapping("/existe/{email}")
    public ResponseEntity<Boolean> buscaUsuario(@PathVariable String email) {
        if (email.isEmpty()) {
            return ResponseEntity.ok(false);
        }
        Optional<Person> user = users.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

//    Movido para SessionController.class    
//    @PostMapping("/autenticar")
//    public boolean autenticar(@RequestBody Usuario u){
//        Optional<Usuario> uDb = users.findByEmail(u.getEmail());
//        if (uDb.isPresent()){
//            if(uDb.get().matchPass(u.getSenha())){
//                return true;
//            }else{
//                return false;
//            }
//        }
//        return false;
//    } 
}
