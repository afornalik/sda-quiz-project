package sda.quiz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.entity.Quiz;
import sda.quiz.service.implementation.AnswerService;
import sda.quiz.service.implementation.QuestionService;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(JUnit4.class)
public class QuestionServiceTest {


    @Mock
    private  IAnswerService answerService  = Mockito.mock(AnswerService.class);

    @InjectMocks
    private QuestionService questionService = new QuestionService(answerService);

    private QuestionDto dummyQuestionDto;
    private List<AnswerDto> dummyAnswersListDto;

    private Question dummyQuestion;
    private List<sda.quiz.entity.Answer> dummyAnswerList;

    @Before
    public void init() {

        dummyQuestion = new Question();

        dummyAnswerList = new ArrayList<>();
        dummyAnswerList.add(new sda.quiz.entity.Answer());
        dummyAnswerList.add(new sda.quiz.entity.Answer());
        dummyAnswerList.add(new sda.quiz.entity.Answer());
        dummyAnswerList.add(new sda.quiz.entity.Answer());

        dummyAnswerList.forEach(answer -> {
            answer.setIsCorrect(true);
            answer.setIdAnswer((long) dummyAnswerList.indexOf(answer));
        });

        dummyQuestion.setAnswerList(dummyAnswerList);

        dummyAnswersListDto = new ArrayList<>();
        dummyAnswersListDto.add(new AnswerDto());
        dummyAnswersListDto.add(new AnswerDto());
        dummyAnswersListDto.add(new AnswerDto());
        dummyAnswersListDto.add(new AnswerDto());

        dummyAnswersListDto.forEach(answerDto -> {
            answerDto.setIsCorrect(true);
            answerDto.setIdAnswer((long) dummyAnswersListDto.indexOf(answerDto));
        });

        dummyQuestionDto = new QuestionDto();

        dummyQuestionDto.setAnswersList(dummyAnswersListDto);

    }

    @Test
    public void shouldSetAllAnswerToFalse() {
        //given
        Mockito.when(answerService.setAnswerToFalse(any(AnswerDto.class))).thenAnswer((Answer<AnswerDto>) invocation -> {
             Object[] arguments = invocation.getArguments();
             if(arguments != null && arguments.length > 0 && arguments[0] != null) {
                 AnswerDto answerDto = (AnswerDto) arguments[0];
                 answerDto.setIsCorrect(false);
                 return  answerDto;
             }
        return null;
        });


        //when
        QuestionDto result = questionService.setAllAnswerToFalse(dummyQuestionDto);

        //then
        Assert.assertFalse(result.getAnswersList().get(0).getIsCorrect());
        Assert.assertFalse(result.getAnswersList().get(1).getIsCorrect());
        Assert.assertFalse(result.getAnswersList().get(2).getIsCorrect());
        Assert.assertFalse(result.getAnswersList().get(3).getIsCorrect());
    }

    @Test
    public void shouldCreateQuestionListWith10EmptyQuestion(){
        //given
        int QUANTITY_OF_QUESTION = 10;
        int QUANTITY_OF_ANSWER = 4;
        List<sda.quiz.entity.Answer> answers = new ArrayList<>();
        Mockito.when(answerService.createAnswerList(dummyQuestion,QUANTITY_OF_ANSWER)).thenReturn(answers);

        //when
        List<Question> result = questionService.createQuestionList(QUANTITY_OF_QUESTION,QUANTITY_OF_ANSWER);

        //than
        Assert.assertEquals(result.size(),QUANTITY_OF_QUESTION);
        Assert.assertEquals(result.get(5).getAnswerList(),answers);
    }


}
