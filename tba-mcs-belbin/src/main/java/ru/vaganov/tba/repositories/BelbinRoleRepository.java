package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vaganov.tba.models.BelbinRole;

public interface BelbinRoleRepository extends JpaRepository<BelbinRole, Long> {
}
