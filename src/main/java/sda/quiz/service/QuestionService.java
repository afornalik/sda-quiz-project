package sda.quiz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.QuestionDto;

import java.util.List;

@Service
@Transactional
public class QuestionService implements IQuestionService {
    @Override
    public boolean createNewQuestion(QuestionDto questionDto) {
        return false;
    }

    @Override
    public List<QuestionDto> showAllAvailableQuestion() {
        return null;
    }
}
