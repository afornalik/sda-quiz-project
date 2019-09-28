package sda.quiz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.entity.Quiz;
import sda.quiz.entity.utilities.Category;
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
    public QuizDto createEmptyQuizWithTwentyQuestions() {
        QuizDto quizDto = new QuizDto();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            QuestionDto questionDto = new QuestionDto();
            List<AnswerDto> answerDtos = new ArrayList<>();
            for (int j = 0; j <= 3; j++) {
                AnswerDto answerDto = new AnswerDto();
                answerDto.setQuestion(questionDto);
                answerDtos.add(answerDto);
            }
            questionDto.setAnswersList(answerDtos);
            questionDtoList.add(questionDto);
        }
        quizDto.setQuestions(questionDtoList);
        return quizDto;
    }

    @Override
    public void saveQuiz(QuizDto quizDto) {
        Quiz quiz = quizMapper.convertDtoToEntity(quizDto);
        quiz.setQuestions(quiz.getQuestions()
                .stream()
                .filter(question -> !question.getQuestion().equals(""))
                .peek(question -> question.getAnswerList()
                        .forEach(answer -> answer.setQuestion(question)))
                .collect(Collectors.toList()));
        quiz.setCreateDate(LocalDate.now());
        quizRepository.save(quiz);

    }

    @Override
    public List<QuizDto> getAllQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(quizMapper::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<QuizDto> getQuizzes(Category category) {
        List<Quiz> quizzes = quizRepository.findByCategory(category);
        return quizzes.stream().map(quizMapper::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public QuizDto getQuizById(Long id, boolean resetAnswer) {
        QuizDto quizDto = quizMapper.convertEntityToDto(quizRepository.findById(id).get());
        if (resetAnswer) {
            quizDto.setQuestions(quizDto.getQuestions().stream().map(questionService::setAllAnswerToFalse).collect(Collectors.toList()));
        }
        return quizDto;
    }

    @Override
    public Map<QuestionDto, Boolean> checkAllAnswer(QuizDto quizDto) {
        Map<QuestionDto, Boolean> answerMap = new HashMap<>();
        QuizDto correctQuiz = quizMapper.convertEntityToDto(quizRepository.findById(quizDto.getIdQuiz()).get());
        for (QuestionDto questionDto : quizDto.getQuestions()) {
            answerMap.put(questionDto, questionDto.equals(correctQuiz.getQuestions().stream().filter(questionDto1 -> questionDto1.getId() == questionDto.getId()).findFirst().get()));

            /*     try {
                answerMap.put(questionDto, questionService.checkAnswerToQuestion(questionRepository.findById(questionDto.getId()).get(), questionDto));
            } catch (MismatchIdException e) {
                e.printStackTrace();
            }*/
        }
        return answerMap;
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

}
