package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.entity.user.User;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.IQuizService;
import sda.quiz.service.implementation.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {



    private final UserService userService;


    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;

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
}
