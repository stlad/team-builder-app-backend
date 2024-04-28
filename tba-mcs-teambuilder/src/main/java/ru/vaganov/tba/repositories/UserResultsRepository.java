package ru.vaganov.tba.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.model.UserFullResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface UserResultsRepository extends JpaRepository<UserFullResult, Long> {

    List<UserFullResult> findAllByProfRoleId(Long profRoleId);

    @Query("SELECT distinct u.profRoleId from UserFullResult u")
    List<Long> findAllProfRoles();

    List<UserFullResult> findAllByTeam_Id(Long teamId);

    @Transactional
    default Map<Long, List<UserFullResult>> findAllGroupedByProfRole() {
        List<Long> roles = findAllProfRoles();
        return roles.stream()
                .collect(Collectors.toMap(r->r, this::findAllByProfRoleId));
    }

    List<UserFullResult> findAllByUserIdIn(List<Long> ids);
}
