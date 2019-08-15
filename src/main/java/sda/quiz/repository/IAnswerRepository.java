package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.Answer;

import java.util.List;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {

    List<Answer> findAllByQuestion(Long id);
}
