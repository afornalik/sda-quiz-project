package sda.quiz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.user.User;
import sda.quiz.service.*;
import sda.quiz.service.implementation.UserService;


@Controller
public class LoginController {


    private final UserService userService;
    private final IQuizService quizService;
    private final IAnswerService answerService;

    @Autowired
    public LoginController(UserService userService, IQuizService quizService, IAnswerService answerService) {
        this.userService = userService;
        this.quizService = quizService;
        this.answerService = answerService;
    }


    @RequestMapping (value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return  modelAndView;
    }

}
