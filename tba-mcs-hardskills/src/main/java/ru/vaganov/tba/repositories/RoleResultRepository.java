package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.models.RoleResult;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleResultRepository extends JpaRepository<RoleResult, Long> {

    Optional<RoleResult> findByUserId(Long userId);

    @Modifying
    void deleteByUserId(Long userId);

    Integer countByHardRole_Id(Long roleId);
}
