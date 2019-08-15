package sda.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.service.mapper.AnswerMapper;
import sda.quiz.service.mapper.IMapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService implements IAnswerService {

    private final IAnswerRepository  answerRepository;
    private final AnswerMapper answerMapper;

    @Autowired
    public AnswerService(IAnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    @Override
    public List<AnswerDto> getAllAnswerForQuestion(Long id) {
        return answerRepository.findAllByQuestion(id).stream().map(answer -> answerMapper.convertEntityToDto(answer)).collect(Collectors.toList());
    }
}
