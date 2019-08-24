package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.user.response.AnswerGiven;

@Repository
public interface IAnswerGivenRepository extends JpaRepository<AnswerGiven,Long> {
}
