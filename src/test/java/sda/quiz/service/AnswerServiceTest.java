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
import sda.quiz.service.implementation.AnswerService;
import sda.quiz.service.implementation.exception.MismatchIdException;

@SpringBootTest
@RunWith(JUnit4.class)
public class AnswerServiceTest {

    private AnswerService answerService;
    private AnswerDto dummyAnswerDto;
    private Answer dummyAnswer;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void init() {
        answerService = new AnswerService();
        dummyAnswerDto = new AnswerDto();
        dummyAnswer = new Answer();
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
    public void shouldCheckAnswerWithTrueResult() throws MismatchIdException {
        //given
        dummyAnswerDto.setIdAnswer(5L);
        dummyAnswerDto.setIsCorrect(true);
        dummyAnswer.setIdAnswer(5L);
        dummyAnswer.setIsCorrect(true);

        //when
        boolean result = answerService.checkAnswer(dummyAnswer,dummyAnswerDto);

        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldCheckAnswerWithFalseResult() throws MismatchIdException {
        //given
        dummyAnswerDto.setIdAnswer(5L);
        dummyAnswerDto.setIsCorrect(false);
        dummyAnswer.setIdAnswer(5L);
        dummyAnswer.setIsCorrect(true);

        //when
        boolean result = answerService.checkAnswer(dummyAnswer,dummyAnswerDto);

        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckNullAnswerWithFalseResult() throws MismatchIdException {
        //given
        dummyAnswerDto.setIdAnswer(5L);
        dummyAnswerDto.setIsCorrect(null);
        dummyAnswer.setIdAnswer(5L);
        dummyAnswer.setIsCorrect(true);

        //when
        boolean result = answerService.checkAnswer(dummyAnswer,dummyAnswerDto);

        //then
        Assert.assertFalse(result);
    }

    @Test()
    public void shouldCheckAnswerAndThrowException() throws MismatchIdException {
        //given
        dummyAnswerDto.setIdAnswer(4L);
        dummyAnswerDto.setIsCorrect(true);
        dummyAnswer.setIdAnswer(5L);
        dummyAnswer.setIsCorrect(true);

        //then
        expectedException.expect(MismatchIdException.class);
        expectedException.expectMessage("Answer ID number mismatch");

        //when
        boolean result = answerService.checkAnswer(dummyAnswer,dummyAnswerDto);


    }
}
