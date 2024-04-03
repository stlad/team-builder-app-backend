package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.models.UserResult;

import java.util.Optional;

@Repository
public interface UserResultsRepository extends JpaRepository<UserResult, Long> {

    Optional<UserResult> findByUserId(Long id);
}
