package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.quiz.entity.user.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
