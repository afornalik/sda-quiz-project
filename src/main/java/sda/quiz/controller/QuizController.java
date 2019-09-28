package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.dto.QuestionDto;
import sda.quiz.dto.QuizDto;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.IQuizService;

import java.util.Map;

@Controller
public class QuizController {
    private final IQuizService quizService;
    private final IQuestionService questionService;

    @Autowired
    public QuizController(IQuizService quizService, IQuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/quiz/add", method = RequestMethod.POST)
    public ModelAndView addQuiz(@ModelAttribute("quiz") QuizDto quizDto, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/home");
        if(result.hasErrors()){
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            modelAndView.setViewName("/quiz/add");
        }else {
            quizService.saveQuiz(quizDto);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/quiz/add")
    public ModelAndView createQuiz() {
        return setModelAndView(new ModelAndView());
    }

    @RequestMapping(value = "/quiz/answer", method = RequestMethod.POST)
    public ModelAndView checkTheAnswers(@ModelAttribute("quiz") QuizDto quiz,BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("/admin/quiz/answer");
        if(result.hasErrors()){

        }

           Map<QuestionDto, Boolean> answerMap = quizService.checkAllAnswer(quiz);
        modelAndView.addObject("answer", answerMap);
        modelAndView.addObject("quizToRun", quiz);
        return modelAndView;
    }

    private ModelAndView setModelAndView(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/quiz/add");
        modelAndView.addObject("quiz", quizService.createEmptyQuizWithTwentyQuestions());
        return modelAndView;
    }


}
