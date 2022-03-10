package de.neuefische.quizdemo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class QuestionServiceTest {

    @Test
    void shouldCreateNewQuestion() {
        Question question = new Question();
        question.setQuestion("Ist MongoDB eine NoSQL Datenbank?");
        question.setCategory("Datenbanken");

        Question questionSaved = new Question();
        questionSaved.setId("777");
        questionSaved.setQuestion("Ist MongoDB eine NoSQL Datenbank?");
        questionSaved.setCategory("Datenbanken");

        QuestionRepository questionRepository = mock(QuestionRepository.class);
        Mockito.when(questionRepository.save(question)).thenReturn(questionSaved);

        QuestionService questionService = new QuestionService(questionRepository);

        Question result = questionService.createNewQuestion(question);

        Assertions.assertThat(result).isSameAs(questionSaved);
    }
}
