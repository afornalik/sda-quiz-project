package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import sda.quiz.entity.Role;
import sda.quiz.entity.User;
import sda.quiz.repository.RoleRepository;
import sda.quiz.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;

@Service ("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(@Qualifier ( "userRepository" ) UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
}
