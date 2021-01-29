
package com.vidula.service;

import com.vidula.model.Permissao;
import com.vidula.model.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vidula.repository.PersonRepository;



@Service
@ComponentScan
public class CustomUserDetailsService implements UserDetailsService {

    
    @Autowired
    private PersonRepository people;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = people.findByEmail(username);
        if(!person.isPresent())
            throw new UsernameNotFoundException("Usuário não encontrado!");
        return new User(person.get().getEmail(), person.get().getPassword(), authorities(person.get().getPermits()));
    }

    private Collection<? extends GrantedAuthority> authorities(List<Permissao> permissoes) {
        List<GrantedAuthority> lista = new ArrayList<>();
        permissoes.forEach((p) -> {
            lista.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        });
        return lista;
    }
    
    
}
