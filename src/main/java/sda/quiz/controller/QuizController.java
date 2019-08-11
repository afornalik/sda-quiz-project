package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.dto.QuizDto;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.IQuizService;

@Controller
public class QuizController {

    private final IQuizService quizService;

    private final IQuestionService questionService;

    @Autowired
    public QuizController(IQuizService quizService, IQuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @RequestMapping(value="admin/quiz/addquiz",method = RequestMethod.GET)
    public ModelAndView createQuiz(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questionList",questionService.getAllQuestions());
        modelAndView.addObject("quiz",quizService.createEmptyQuiz());
        modelAndView.setViewName("admin/quiz/addQuizForm");
        return modelAndView;
    }

    @RequestMapping(value = "admin/quiz/addquiz",method = RequestMethod.POST)
    public ModelAndView addQuizAfterQuizForm(@ModelAttribute("quiz")QuizDto quizDto, @RequestParam("questionsToAdd") Long[] questionToAdd){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/home");
        quizService.saveQuiz(quizDto,questionToAdd);
        return modelAndView;
    }



}
