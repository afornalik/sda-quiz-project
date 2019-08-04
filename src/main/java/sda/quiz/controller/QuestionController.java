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

    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }


    @RequestMapping(value = "/addquestion",method = RequestMethod.POST)
    public ModelAndView addQuestion(@ModelAttribute ("question")QuestionDto questionDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            questionService.createNewQuestion(questionDto);
            modelAndView.setViewName("addQuestionConfirm");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("addQuestionForm");
        }
        return modelAndView;
    }
}
