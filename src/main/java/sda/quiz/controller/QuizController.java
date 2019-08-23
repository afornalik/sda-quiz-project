package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    public ModelAndView addQuizAfterQuizForm(@ModelAttribute("quiz")QuizDto quizDto,
                                             @RequestParam(name = "questionsToAdd" , required = false) Long[] questionToAdd,
                                             @RequestParam(name = "addQuestion",required = false) String addQuestion){

        ModelAndView modelAndView = new ModelAndView();
        if(addQuestion != null) {
            modelAndView.setViewName("redirect:/admin/addquestion");
        }else {
            modelAndView.setViewName("redirect:/admin/home");
            quizService.saveQuiz(quizDto, questionToAdd);
        }
        return modelAndView;
    }

    @RequestMapping(value="admin/quiz/checkAnswer",method =RequestMethod.POST)
    public ModelAndView checkTheAnswers(@ModelAttribute("quiz")QuizDto quiz){
        ModelAndView modelAndView = new ModelAndView();
       QuizDto quizDto = quizService.getQuizById(quiz.getIdQuiz(),false);
        Map<QuestionDto,Boolean> answerMap = quizService.checkAllAnswer(quiz);
        modelAndView.addObject("answer",answerMap);
        modelAndView.setViewName("admin/quiz/checkAnswer");
        return modelAndView;
    }




}
