package sda.quiz.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.mapper.implementation.QuestionMapper;

@SpringBootTest
@RunWith(JUnit4.class)
public class QuestionServiceTest {

    @Mock
    private  IQuestionRepository questionRepository;

    @Mock
    private  IAnswerService answerService;

    @Mock
    private  QuestionMapper questionMapper;


    @Before
    public void init() {

    }


}
