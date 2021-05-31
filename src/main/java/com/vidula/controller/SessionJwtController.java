package com.vidula.controller;

import com.vidula.DTO.PersonDTO;
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
import com.vidula.model.Person;
import com.vidula.service.CustomUserDetailsService;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import com.vidula.repository.PersonRepository;

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
    private PersonRepository usuarios;

    @PostMapping("/sessions")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Person user) throws Exception {
        authenticate(user.getEmail(), user.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        Optional<Person> allInfoUser = usuarios.findByEmail(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(token,
                        new PersonDTO(allInfoUser.get().getId(), allInfoUser.get().getName(), allInfoUser.get().getEmail(), allInfoUser.get().getLevelAccess(), allInfoUser.get().getAvatarUrl(), allInfoUser.get().getGender())
                    ));

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
