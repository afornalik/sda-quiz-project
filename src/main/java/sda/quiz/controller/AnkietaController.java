package sda.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sda.quiz.entity.Ankiety;
import sda.quiz.service.AnkietyService;

@Controller
public class AnkietaController {

   //private final IAnkietyService ankietyService;

    @Autowired
    private AnkietyService ankietyService;

   // @Autowired
    private Ankiety ankiety;

    @RequestMapping(value = "/addAnkiete", method = RequestMethod.GET)
    public ModelAndView addAnkiete(){
        Ankiety ankiety = new Ankiety();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ankiety",ankiety);
        return modelAndView;
    }

    @RequestMapping(value = "/addAnkiete", method = RequestMethod.POST)
    public ModelAndView addAnkiete(Ankiety ankiety, BindingResult bindingResult){
       ModelAndView modelAndView = new ModelAndView();
       ankietyService.saveAnkiety(ankiety);
       modelAndView.addObject("successMessage","Ankieta zapisana");
       modelAndView.addObject("ankiety",ankiety);

       return modelAndView;
    }
}