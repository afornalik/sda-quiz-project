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
import sda.quiz.entity.utilities.Category;
import sda.quiz.service.IQuizService;
import sda.quiz.service.implementation.UserService;

@Controller
public class LoggedController {

    private final IQuizService quizService;

    @Autowired
    public LoggedController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping(value = "/admin/home")
    public ModelAndView home(@RequestParam(name = "delete", required = false) Long quizToDelete,
                             @RequestParam(name = "run", required = false) Long quizToRun,
                             @RequestParam(name = "create", required = false) String create,
                             @RequestParam(name = "category", required = false) String category) {

        ModelAndView modelAndView = new ModelAndView("admin/home");

        if (category != null && !category.equals("all")) {
            modelAndView.addObject("quizList", quizService.getQuizzesByCategory(Category.valueOf(category)));
            return modelAndView;

        } else if (quizToRun != null) {
            modelAndView.addObject("quizToRun", quizService.getQuizById(quizToRun, true));
            modelAndView.setViewName("admin/quiz/run");

        } else if (quizToDelete != null) {
            quizService.deleteQuiz(quizToDelete);
        } else if (create != null) {
            modelAndView.setViewName("redirect:/quiz/add");
        }

        modelAndView.addObject("quizList", quizService.getAllQuizzes());
        return modelAndView;
    }
}
