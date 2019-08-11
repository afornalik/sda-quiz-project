package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Quiz;
import sda.quiz.repository.IQuizRepository;
import sda.quiz.service.mapper.IMapper;
import sda.quiz.service.mapper.QuizMapper;

@Service
@Transactional
public class QuizService implements IQuizService{


    private final IQuizRepository quizRepository;

    private final QuizMapper quizMapper;

    @Autowired
    public QuizService(IQuizRepository quizRepository, QuizMapper  quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }


    @Override
    public QuizDto createEmptyQuiz() {
        return new QuizDto();
    }

    @Override
    public void saveQuiz(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz =  quizMapper.convertDtoToEntity(quizDto);
    }
}
