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
    List<RoleResult> findAllByUserIdAndPositionIn(Long userId, List<Integer> positions);
    Optional<RoleResult> findByUserIdAndPosition(Long userId, Integer position);

    @Modifying
    void deleteByUserId(Long userId);
}
