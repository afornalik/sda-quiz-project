package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.entity.utilities.Category;
import sda.quiz.service.IQuizService;

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
        modelAndView.addObject("randomQuizzes",quizService.getQuizzes(5));

        if (category != null && !category.equals("all")) {
            modelAndView.addObject("quizList", quizService.getQuizzes(Category.valueOf(category)));
            return modelAndView;

        } else if (quizToRun != null) {
            modelAndView.addObject("quizToRun", quizService.getQuizById(quizToRun, true));
            modelAndView.setViewName("all/quiz/run");

        } else if (quizToDelete != null) {
            quizService.deleteQuiz(quizToDelete);
        } else if (create != null) {
            modelAndView.setViewName("redirect:/quiz/add");
        }

        modelAndView.addObject("quizList", quizService.getQuizzes());
        return modelAndView;
    }
}
