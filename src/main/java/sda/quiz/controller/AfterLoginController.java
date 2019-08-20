package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.user.User;
import sda.quiz.service.IQuizService;
import sda.quiz.service.implementation.UserService;

@Controller
public class AfterLoginController {

    private final UserService userService;
    private final IQuizService quizService;

    @Autowired
    public AfterLoginController(UserService userService, IQuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
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
