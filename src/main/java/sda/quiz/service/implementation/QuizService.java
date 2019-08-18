package sda.quiz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.QuestionDto;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Question;
import sda.quiz.entity.Quiz;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.repository.IQuizRepository;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.IQuizService;
import sda.quiz.service.implementation.exception.MismatchIdException;
import sda.quiz.service.mapper.implementation.QuizMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizService implements IQuizService {


    private final IQuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final IQuestionRepository questionRepository;
    private final IQuestionService questionService;

    @Autowired
    public QuizService(IQuizRepository quizRepository, QuizMapper quizMapper, IQuestionRepository questionRepository, IAnswerService answerService, IQuestionService questionService) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }


    @Override
    public QuizDto createEmptyQuiz() {
        return new QuizDto();
    }

    @Override
    public void saveQuiz(QuizDto quizDto, Long[] questionIdList) {
        List<Question> questionList = new ArrayList<>();
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
    public QuizDto getQuizById(Long id, boolean resetAnswer) {
        QuizDto quizDto = quizMapper.convertEntityToDto(quizRepository.findById(id).get());
        if(resetAnswer) {
            quizDto.setQuestions(quizDto.getQuestions().stream().map(questionService::setAllAnswerToFalse).collect(Collectors.toList()));
        }
        return quizDto;
    }

    @Override
    public Map<QuestionDto, Boolean> checkAllAnswer(QuizDto quizDto) {
        Map<QuestionDto, Boolean> answerMap = new HashMap<>();
       for(QuestionDto questionDto : quizDto.getQuestions()){
           try {
               answerMap.put(questionDto,questionService.checkAnswerToQuestion(questionRepository.findById(questionDto.getId()).get(),questionDto));
           } catch (MismatchIdException e) {
               e.printStackTrace();
           }
       }
        return answerMap;
    }


}
