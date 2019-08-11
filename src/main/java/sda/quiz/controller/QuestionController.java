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

    @RequestMapping(value = "admin/question/showAllQuestion",method = RequestMethod.GET)
    public ModelAndView showAllQuestions() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questions",questionService.getAllQuestions());
        modelAndView.setViewName("admin/question/showAllQuestion");
        return modelAndView;
    }

    @RequestMapping(value = "admin/addquestion",method = RequestMethod.GET)
    public ModelAndView addQuestionEmptyForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("question",questionService.createEmptyQuestionWith4Answer());
        modelAndView.setViewName("admin/question/addQuestionForm");
        return modelAndView;
    }


    @RequestMapping(value = "/addquestion",method = RequestMethod.POST)
    public ModelAndView addQuestionAfterInsertForm(@ModelAttribute ("question")QuestionDto questionDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            questionService.saveNewQuestion(questionDto);
            modelAndView.setViewName("admin/question/addQuestionConfirm");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("admin/question/addQuestionForm");
        }
        return modelAndView;
    }

}
