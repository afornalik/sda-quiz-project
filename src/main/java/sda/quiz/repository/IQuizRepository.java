package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.Quiz;

@Repository
public interface IQuizRepository extends JpaRepository<Quiz,Long> {
}
