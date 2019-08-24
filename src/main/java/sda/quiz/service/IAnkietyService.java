package sda.quiz.service;

import sda.quiz.entity.Ankiety;

public interface IAnkietyService {
    Ankiety createEmptyAnkiety();

    void saveAnkiety(Ankiety ankiety);
}
