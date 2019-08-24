package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.Ankiety;

@Repository
public interface IAnkietyRepository extends JpaRepository<Ankiety,Long> {
}
