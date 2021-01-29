package com.vidula.repository;

import com.vidula.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    public Person findByEmailStartingWith(String usuario);
    public Optional<Person> findByEmail(String email);
}
