package sda.quiz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.mapper.implementation.AnswerMapper;
import sda.quiz.service.mapper.implementation.QuestionMapper;
import sda.quiz.service.validator.IValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class QuestionService implements IQuestionService {


    private final IQuestionRepository questionRepository;
    private final IAnswerRepository  answerRepository;
    private final IAnswerService answerService;
    private final IValidator<QuestionDto> questionDtoIValidator;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Autowired
    public QuestionService(IQuestionRepository questionRepository, IAnswerRepository answerRepository, IAnswerService answerService, IValidator<QuestionDto> questionDtoIValidator, QuestionMapper questionMapper, AnswerMapper answerMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.answerService = answerService;
        this.questionDtoIValidator = questionDtoIValidator;
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
    }

    @Override
    public void saveNewQuestion(QuestionDto questionDto) throws Exception {
        if (questionDtoIValidator.isCorrect(questionDto)) {
            Question question = questionMapper.convertDtoToEntity(questionDto);
            question.setAnswerList(questionDto.getAnswersList().stream().map(answerDto -> answerMapper.convertDtoToEntity(answerDto)).collect(Collectors.toList()));
            question.getAnswerList().forEach(answer -> {
                answer.setQuestion(question);
                answerRepository.save(answer);
            });
            questionRepository.save(question);

            System.out.println(question.getIdQuestion());


        }
    }

    @Override
    public QuestionDto createEmptyQuestionWith4Answer() {
     return new QuestionDto(null,null,null, Arrays.asList(new AnswerDto(),new AnswerDto(),new AnswerDto(),new AnswerDto()),null);
    }

    @Override
    public Set<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream().map(questionMapper::convertEntityToDto).collect(Collectors.toSet());
    }

    @Override
    public List<QuestionDto> showAllAvailableQuestion() {
        return null;
    }

    @Override
    public QuestionDto setAllAnswerToFalse(QuestionDto questionDto) {
        questionDto.setAnswersList(questionDto.getAnswersList().stream().map(answerService::setAnswerToFalse).collect(Collectors.toList()));
      return questionDto;

    }
}