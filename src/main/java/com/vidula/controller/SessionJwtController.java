package com.vidula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidula.security.JwtTokenUtil;
import com.vidula.model.JwtResponse;
import com.vidula.model.Usuario;
import com.vidula.repository.UsuarioRepository;
import com.vidula.service.CustomUserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin
public class SessionJwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private UsuarioRepository usuarios;

    @PostMapping("/sessions")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario user) throws Exception {
        authenticate(user.getEmail(), user.getSenha());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        user.setNome(usuarios.findByEmail(user.getEmail()).get().getNome());
        return ResponseEntity.ok(new JwtResponse(token,user.getNome()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
