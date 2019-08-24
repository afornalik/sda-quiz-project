package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.quiz.entity.user.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}

