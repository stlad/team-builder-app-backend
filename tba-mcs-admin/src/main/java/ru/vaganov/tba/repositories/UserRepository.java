package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vaganov.tba.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
