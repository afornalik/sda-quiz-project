package sda.quiz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.entity.Quiz;
import sda.quiz.repository.IQuizRepository;
import sda.quiz.service.implementation.QuizService;
import sda.quiz.service.mapper.implementation.QuestionMapper;
import sda.quiz.service.mapper.implementation.QuizMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuizServiceTest {

    private static final int QUANTITY_OF_QUESTION = 20;
    private static final int QUANTITY_OF_ANSWER = 4;
    private List<Question> dummyQuestionList;
    private List<Answer> dummyAnswerList;


    @Mock
    private IQuizRepository quizRepository;

    @Mock
    private QuizMapper quizMapper;

    @Mock
    private IQuestionService questionService;

    @InjectMocks
    private QuizService quizService;

    @Autowired
    private QuestionMapper questionMapper;

    @Before
    public void init() {
        fillAnswerList();
        fillQuestionList();
    }


    @Test
    public void shouldCreateQuizDtoWith20QuestionAnd4AnswerEach() {
        //given
        //Mockito.when(questionService.createQuestionList(QUANTITY_OF_QUESTION,QUANTITY_OF_ANSWER)).thenReturn(dummyQuestionList);
        QuizDto quizDto = new QuizDto();
        quizDto.setQuestions(dummyQuestionList.stream().map(questionMapper::convertEntityToDto).collect(Collectors.toList()));
        Mockito.when(quizMapper.convertEntityToDto(any(Quiz.class))).thenReturn(quizDto);

        //when
        QuizDto result = quizService.createEmptyQuiz(QUANTITY_OF_QUESTION,QUANTITY_OF_ANSWER);

        //then
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getQuestions().size(),QUANTITY_OF_QUESTION);
        Assert.assertEquals(result.getQuestions().get(1).getAnswersList().size(),QUANTITY_OF_ANSWER);
    }



    private void fillQuestionList() {
        dummyQuestionList = new ArrayList<>();
        for(int i = 0 ; i <QUANTITY_OF_QUESTION; i++) {
            dummyQuestionList.add(new Question());
            dummyQuestionList.forEach(question -> {
                question.setAnswerList(dummyAnswerList);
                question.getAnswerList().forEach(answer -> answer.setQuestion(question));
            });
        }
    }

    private void fillAnswerList() {
        dummyAnswerList = new ArrayList<>();
        for(int i = 0 ; i <QUANTITY_OF_ANSWER; i++) {
            dummyAnswerList.add(new Answer());
        }
    }
}


