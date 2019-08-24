package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.user.response.QuizTaken;

@Repository
public interface IQuizTakenRepository extends JpaRepository<QuizTaken,Long> {
}
