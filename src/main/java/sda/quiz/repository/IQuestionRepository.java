package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.Question;

@Repository
public interface IQuestionRepository extends JpaRepository<Question,Long> {
}
