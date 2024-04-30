package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.model.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT p.id FROM Project p")
    List<Long> getAllProjectIds();
}
