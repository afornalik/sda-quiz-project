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
import sda.quiz.entity.User;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.IQuizService;
import sda.quiz.service.QuestionService;
import sda.quiz.service.UserService;


@Controller
public class LoginController {


    private final UserService userService;
    private final IQuizService quizService;

    @Autowired
    public LoginController(UserService userService,  IQuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }


    @RequestMapping (value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
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
                .rejectValue("email","error.user", "Podany adres jest już zarejestrowany.");
            bindingResult.rejectValue("password","error.password","Podane hasło jest za krótkie, min 5 znaków.");
            modelAndView.setViewName("registration");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został zarejestrowany prawidłowo.");
            modelAndView.addObject("user",user);
            modelAndView.setViewName("registration");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.POST)
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
            modelAndView.addObject("quiz",quizService.getQuizById(quizToRun));
            modelAndView.setViewName("admin/quiz/runQuiz");
            return modelAndView;
        }

        modelAndView.addObject("quizList",quizService.getAllQuiz());
        modelAndView.addObject("userName", "Witam na naszej stronie " + user.getName());
        modelAndView.addObject("adminMessage","Content available only for users with admin role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

 /*   @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView goToHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }*/









}
