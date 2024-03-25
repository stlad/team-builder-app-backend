package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.models.HardRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface HardRoleRepository extends JpaRepository<HardRole, Long> {
    Optional<HardRole> findByEngName(String engName);
    Optional<HardRole> findByRusName(String rusName);
    Optional<HardRole> findByRusNameOrEngName(String rusName, String engName);

    List<HardRole> findByIndustry(HardRole.Industry industry);
}
