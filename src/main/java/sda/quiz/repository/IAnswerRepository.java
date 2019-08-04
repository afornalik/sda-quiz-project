package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.Answer;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {
}
