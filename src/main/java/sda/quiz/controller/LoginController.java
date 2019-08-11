package sda.quiz.controller;

import javax.validation.Valid;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.User;
import sda.quiz.service.QuestionService;
import sda.quiz.service.QuizService;
import sda.quiz.service.UserService;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;



    @RequestMapping (value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return  modelAndView;
    }

    @RequestMapping(value="/registration",method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value ="/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null) {
            bindingResult
                .rejectValue("email","error.user", "There is already a user with thath email provider");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user",user);
            modelAndView.setViewName("registration");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Witam na naszej stronie " + user.getName());
        modelAndView.addObject("adminMessage","Content available only for users with admin role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = "/ankiety", method = RequestMethod.GET)
    public ModelAndView ankiety(){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

//    @RequestMapping (value = "/quiz", method = RequestMethod.GET)
//    public ModelAndView quiz(){
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//    }
    @RequestMapping(value = "/quiz",method = RequestMethod.GET)
    public ModelAndView quiz(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("quiz");
        System.out.println("sdfsdfsdf");

        return modelAndView;
    }
    @RequestMapping(value = "/showQuestions",method = RequestMethod.GET)
    public ModelAndView showAllQuestions() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questions",questionService.getAllQuestions());
        modelAndView.setViewName("question/showAllQuestion");
        return modelAndView;
    }

    @RequestMapping(value = "/addQuestion",method = RequestMethod.GET)
    public ModelAndView addQuestionEmptyForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("question",questionService.createEmptyQuestionWith4Answer());
        modelAndView.setViewName("question/addQuestionForm");
        return modelAndView;
    }


    @RequestMapping(value = "/addQuestion",method = RequestMethod.POST)
    public ModelAndView addQuestionAfterInsertForm(@ModelAttribute ("question") QuestionDto questionDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            questionService.saveNewQuestion(questionDto);
            modelAndView.setViewName("question/addQuestionConfirm");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("question/addQuestionForm");
        }
        return modelAndView;
    }








}
