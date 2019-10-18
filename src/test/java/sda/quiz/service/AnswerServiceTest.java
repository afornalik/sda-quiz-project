package sda.quiz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.service.implementation.AnswerService;

import java.util.List;

@SpringBootTest
@RunWith(JUnit4.class)
public class AnswerServiceTest {

    private AnswerService answerService;
    private AnswerDto dummyAnswerDto;
    private Question dummyQuestion;


    @Before
    public void init() {
        answerService = new AnswerService();
        dummyAnswerDto = new AnswerDto();
        dummyQuestion = new Question();
    }

    @Test
    public void shouldSetAnswerToFalse() {
        //given
        dummyAnswerDto.setIsCorrect(true);

        //when
        AnswerDto result = answerService.setAnswerToFalse(dummyAnswerDto);

        //then
        Assert.assertEquals(result.getIsCorrect(),dummyAnswerDto.getIsCorrect());
        Assert.assertFalse(dummyAnswerDto.getIsCorrect());
        Assert.assertFalse(result.getIsCorrect());
    }

    @Test
    public void shouldCreateAnswerListWith4EmptyAnswer(){
        //given
        int QUANTITY_OF_ANSWER = 4;

        //when
        List<Answer> result = answerService.createAnswerList(dummyQuestion,QUANTITY_OF_ANSWER);

        //then
        Assert.assertEquals(result.size(),QUANTITY_OF_ANSWER);
        Assert.assertEquals(result.get(0).getQuestion(),dummyQuestion);
        Assert.assertEquals(result.get(1).getQuestion(),dummyQuestion);
        Assert.assertEquals(result.get(2).getQuestion(),dummyQuestion);
        Assert.assertEquals(result.get(3).getQuestion(),dummyQuestion);
    }


}
