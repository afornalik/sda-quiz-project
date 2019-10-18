package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.user.response.AnswerDone;

@Repository
public interface IAnswerDoneRepository extends JpaRepository<AnswerDone,Long> {
}
