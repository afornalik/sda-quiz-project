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
    public ModelAndView home(@RequestParam(name = "delete",required = false) Long quizToDelete,
                             @RequestParam(name = "change",required = false) Integer quizToChange,
                             @RequestParam(name = "run",required = false) Long quizToRun) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if(quizToDelete != null) {
            quizService.deleteQuiz(quizToDelete);
        }
        if(quizToRun != null) {
            QuizDto quizDto = quizService.getQuizById(quizToRun,true);
            modelAndView.addObject("quiz",quizDto);
            modelAndView.setViewName("admin/quiz/runQuiz");
            return modelAndView;
        }

        modelAndView.addObject("quizList",quizService.getAllQuiz());
        modelAndView.addObject("userName", "Witam na naszej stronie " + user.getName());
        modelAndView.addObject("adminMessage","Content available only for users with admin role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }









}
