package sda.quiz.service;

import sda.quiz.entity.user.User;

public interface IUserService  {

    User findUserByEmail(String email);

    User saveUser(User user);
}
