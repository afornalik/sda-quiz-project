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
import sda.quiz.entity.Quiz;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.IQuizService;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class QuizController {
    private final IQuizService quizService;

    @Autowired
    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
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
    public ModelAndView checkTheAnswers(@ModelAttribute("quizToRun") QuizDto quiz,
                                        @RequestParam(name = "backToMenu", required = false) String backToMenu,
                                        BindingResult result) {

        ModelAndView modelAndView = new ModelAndView("all/quiz/answer");
        if(result.hasErrors()){

        }
        if(backToMenu!=null) {
            modelAndView.setViewName("redirect:/admin/home");
        }
          Map<QuestionDto, Boolean> answerMap = quizService.checkAllAnswer(quiz);
        modelAndView.addObject("answer", answerMap);
        modelAndView.addObject("quizToRun", quiz);
        return modelAndView;
    }

    private ModelAndView setModelAndView(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/quiz/add");
        modelAndView.addObject("quiz", quizService.createEmptyQuiz(20,4));
        return modelAndView;
    }



}
