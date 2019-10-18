package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.user.response.QuizDone;

@Repository
public interface IQuizDoneRepository extends JpaRepository<QuizDone,Long> {
}
