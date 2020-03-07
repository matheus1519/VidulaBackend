package com.vidula.repository;

import com.vidula.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByEmailStartingWith(String usuario);
    public Optional<Usuario> findByEmail(String email);
}
