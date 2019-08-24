package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.entity.Ankiety;
import sda.quiz.entity.User;
import sda.quiz.repository.IAnkietyRepository;

@Service
@Transactional
public class AnkietyService implements IAnkietyService {

    private final IAnkietyRepository ankietyRepository;


    @Autowired
    public AnkietyService(IAnkietyRepository ankietyRepository){
        this.ankietyRepository = ankietyRepository;
    }
    @Override
    public Ankiety createEmptyAnkiety(){

        return new Ankiety();
    }
    @Override
    public void saveAnkiety(Ankiety ankiety) {

        //User user;
         //user.getName();

        ankiety.getNameAnkiety().trim();
        ankiety.getNameAnkiety().concat(" Mamy to");

        ankiety.setNameAnkiety(ankiety.getNameAnkiety().trim()+"ttt");
        System.out.println("save ankiety - service "+ankiety.getNameAnkiety());
  //      Ankiety ankiety1 = new Ankiety();
        ankietyRepository.save(ankiety);
    }
}