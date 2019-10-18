package sda.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.service.implementation.QuizService;

@Controller
public class IndexController {

    private final QuizService quizService;

    public IndexController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("randomQuizzes",quizService.getQuizzes(5));
        return modelAndView;
    }
}