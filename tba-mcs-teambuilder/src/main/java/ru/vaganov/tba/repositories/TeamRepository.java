package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
