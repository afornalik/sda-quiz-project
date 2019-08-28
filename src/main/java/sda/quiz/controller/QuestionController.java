package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.dto.QuestionDto;
import sda.quiz.service.IQuestionService;

@Controller
public class QuestionController {

    private final IQuestionService questionService;

    @Autowired
    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/question/showAll")
    public ModelAndView showAllQuestions() {
        ModelAndView modelAndView = new ModelAndView("/admin/question/showAll");
        modelAndView.addObject("questions", questionService.getAllQuestions());
        return modelAndView;
    }

    @RequestMapping(value = "/question/add")
    public ModelAndView addQuestionEmptyForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/question/add");
        modelAndView.addObject("question", new QuestionDto());
        return modelAndView;
    }

    // TO DO create proper validation
    @RequestMapping(value = "/question/add", method = RequestMethod.POST)
    public ModelAndView addQuestionAfterInsertForm(@ModelAttribute("question") QuestionDto questionDto) {
        ModelAndView modelAndView = new ModelAndView("/admin/question/add");
        try {
            questionService.saveNewQuestion(questionDto);
            modelAndView.setViewName("redirect:/quiz/add");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

}
