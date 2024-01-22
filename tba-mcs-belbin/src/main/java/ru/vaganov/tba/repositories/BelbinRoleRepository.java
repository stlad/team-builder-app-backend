package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.Question;

import java.util.Optional;

@Repository
public interface BelbinRoleRepository extends JpaRepository<BelbinRole, Long> {
    Optional<BelbinRole> findByEngName(String engName);
    Optional<BelbinRole> findByRusName(String rusName);
    Optional<BelbinRole> findByNumber(Long number);
}
