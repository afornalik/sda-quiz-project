package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.QuestionDto;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Question;
import sda.quiz.entity.Quiz;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.repository.IQuizRepository;
import sda.quiz.service.mapper.QuizMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizService implements IQuizService{


    private final IQuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final IQuestionRepository questionRepository;

    @Autowired
    public QuizService(IQuizRepository quizRepository, QuizMapper quizMapper, IQuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.questionRepository = questionRepository;
    }


    @Override
    public QuizDto createEmptyQuiz() {
        return new QuizDto();
    }

    @Override
    public void saveQuiz(QuizDto quizDto, Long[] questionIdList) {
        Set<Question> questionList = new HashSet<>();
        Quiz quiz = quizMapper.convertDtoToEntity(quizDto);
        for(Long i : questionIdList){
            questionList.add(questionRepository.findById(i).get());
        }
        quiz.setQuestions(questionList);
        quiz.setCreateDate(LocalDate.now());
        quizRepository.save(quiz);

    }

    @Override
    public List<QuizDto> getAllQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(quizMapper::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public QuizDto getQuizById(Long id) {
        return quizMapper.convertEntityToDto(quizRepository.findById(id).get());
    }


}
