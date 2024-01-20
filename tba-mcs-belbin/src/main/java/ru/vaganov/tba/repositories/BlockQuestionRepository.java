package ru.vaganov.tba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vaganov.tba.models.BlockQuestion;
import ru.vaganov.tba.models.Question;

import java.util.Optional;

@Repository
public interface BlockQuestionRepository extends JpaRepository<BlockQuestion, Long> {
    Optional<BlockQuestion> findByNumber(Long number);

}
