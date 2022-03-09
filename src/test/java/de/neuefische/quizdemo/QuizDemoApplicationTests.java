package de.neuefische.quizdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizDemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        Question question = new Question();
        question.setQuestion("Was ist ein Compiler?");
        question.setCategory("Java");

        ResponseEntity<Question> postResponse = restTemplate.postForEntity("/api/questions", question, Question.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(postResponse.getBody().getId()).isNotNull();
        assertThat(postResponse.getBody().getQuestion()).isEqualTo("Was ist ein Compiler?");
        assertThat(postResponse.getBody().getCategory()).isEqualTo("Java");

        ResponseEntity<Question> getResponse = restTemplate.getForEntity("/api/questions/" + postResponse.getBody().getId(), Question.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getId()).isNotNull();
        assertThat(getResponse.getBody().getQuestion()).isEqualTo("Was ist ein Compiler?");
        assertThat(getResponse.getBody().getCategory()).isEqualTo("Java");
    }

}
