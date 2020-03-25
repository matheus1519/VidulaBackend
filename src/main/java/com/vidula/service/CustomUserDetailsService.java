
package com.vidula.service;

import com.vidula.model.Permissao;
import com.vidula.model.Usuario;
import com.vidula.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    
    @Autowired
    private UsuarioRepository usuarios;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarios.findByEmail(username);
        if(!usuario.isPresent())
            throw new UsernameNotFoundException("Usuário não encontrado!");
        return new User(usuario.get().getEmail(), usuario.get().getSenha(), authorities(usuario.get().getPermissoes()));
    }

    private Collection<? extends GrantedAuthority> authorities(List<Permissao> permissoes) {
        List<GrantedAuthority> lista = new ArrayList<>();
        for(Permissao p : permissoes){
            lista.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        }
        return lista;
    }
    
    
}
