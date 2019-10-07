package sda.quiz.service;


import sda.quiz.dto.QuestionDto;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Quiz;
import sda.quiz.entity.utilities.Category;
import sda.quiz.service.mapper.IMapper;

import java.util.List;
import java.util.Map;

public interface  IQuizService {



    QuizDto createEmptyQuiz(int quantityOfQuestion, int quantityOfAnswer);

    QuizDto saveQuiz(QuizDto quizDto);

    /**
     * Get All available quizzes
     * */
    List<QuizDto> getAllQuizzes();


    List<QuizDto> getQuizzesByCategory(Category category);



    void deleteQuiz(Long id);

    QuizDto getQuizById(Long id ,boolean resetAnswer);

    Map<QuestionDto, Boolean> checkAllAnswer(QuizDto quizDto);


}
