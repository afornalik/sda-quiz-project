package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.Quiz;
import sda.quiz.entity.utilities.Category;

import java.util.List;

@Repository
public interface IQuizRepository extends JpaRepository<Quiz,Long> {

    List<Quiz> findByCategory(Category category);
}
