package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.mapper.AnswerMapper;
import sda.quiz.service.mapper.Mapper;
import sda.quiz.service.mapper.QuestionMapper;
import sda.quiz.service.validator.IValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class QuestionService implements IQuestionService {


    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IAnswerRepository  answerRepository;

    @Autowired
    private IValidator<QuestionDto> questionDtoIValidator;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

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
     return new QuestionDto(null,null,null, Arrays.asList(new AnswerDto(),new AnswerDto(),new AnswerDto(),new AnswerDto()));
    }

    @Override
    public List<QuestionDto> showAllAvailableQuestion() {
        return null;
    }
}
