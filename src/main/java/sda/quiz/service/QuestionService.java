package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.mapper.Mapper;
import sda.quiz.service.mapper.QuestionMapper;
import sda.quiz.service.validator.IValidator;

import java.util.List;




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

    @Override
    public boolean createNewQuestion(QuestionDto questionDto) throws Exception {
        if (questionDtoIValidator.isCorrect(questionDto)) {
            questionRepository.save(questionMapper.convertDtoToEntity(questionDto));
        }

        return false;
    }

    @Override
    public List<QuestionDto> showAllAvailableQuestion() {
        return null;
    }
}
