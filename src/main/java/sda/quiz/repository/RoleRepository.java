package sda.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.quiz.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}

