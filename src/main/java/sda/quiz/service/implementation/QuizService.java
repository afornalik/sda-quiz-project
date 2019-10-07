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
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizService implements IQuizService {


    private final IQuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final IQuestionService questionService;

    @Autowired
    public QuizService(IQuizRepository quizRepository, QuizMapper quizMapper, IQuestionService questionService) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.questionService = questionService;
    }


    @Override
    public QuizDto createEmptyQuiz(int quantityOfQuestion, int quantityOfAnswer) {
        Quiz quiz = new Quiz();
        quiz.setQuestions(fillQuiz(quantityOfQuestion, quantityOfAnswer));
        return quizMapper.convertEntityToDto(quiz);
    }

    private List<Question> fillQuiz( int quantityOfQuestion, int quantityOfAnswer) {
        return questionService.createQuestionList(quantityOfQuestion, quantityOfAnswer);
    }

    @Override
    public QuizDto saveQuiz(QuizDto quizDto) {
        Quiz quiz = quizMapper.convertDtoToEntity(quizDto);
        quiz.setQuestions(quiz.getQuestions()
                .stream()
                .filter(question -> !question.getQuestion().equals(""))
                .peek(question -> question.getAnswerList()
                        .forEach(answer -> answer.setQuestion(question)))
                .peek(question -> question.setQuiz(question.getQuiz()))
                .collect(Collectors.toList()));
        quiz.setCreateDate(LocalDate.now());
        quizRepository.save(quiz);
        return quizMapper.convertEntityToDto(quiz);

    }

    @Override
    public List<QuizDto> getQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(quizMapper::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<QuizDto> getQuizzes(int numberOfQuizzes) {
        List<Quiz> quizzes = quizRepository.findAll();
        List<Quiz> randomQuizzes = new ArrayList<>();
        Random random = new Random();
        for(int i = 0 ; i < numberOfQuizzes; i++) {
           randomQuizzes.add(quizzes.get(random.nextInt(quizzes.size())));
        }
        return randomQuizzes.stream().map(quizMapper::convertEntityToDto).collect(Collectors.toList());
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
        quizDto.setQuestions(quizDto.getQuestions()
                .stream()
                .peek(questionDto -> questionDto.setQuiz(correctQuiz.getQuestions()
                        .stream()
                        .filter(questionDto1 -> questionDto1.getId().equals(questionDto.getId()))
                        .findFirst()
                        .get()
                        .getQuiz()))
                .collect(Collectors.toList()));
        for (QuestionDto questionDto : quizDto.getQuestions()) {
            answerMap.put(questionDto,
                    questionDto.equals(correctQuiz.getQuestions()
                            .stream()
                            .filter(questionDto1 -> questionDto1.getId().equals(questionDto.getId()))
                            .findFirst()
                            .get()));


        }
        return answerMap;
    }



    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }


}
