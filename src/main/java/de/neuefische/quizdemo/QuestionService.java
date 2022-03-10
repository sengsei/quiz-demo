package de.neuefische.quizdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question createNewQuestion(Question question){
        return questionRepository.save(question);
    }

    public Optional<Question> findById(String id){
        return questionRepository.findById(id);
    }

    public List<Question> findByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Question updateQuestion(String id, Question question) {
        Optional<Question> questionNew = questionRepository.findById(id);
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> patchQuestion(String id, Question question) {

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question questionFromDatabase = optionalQuestion.get();
            if (question.getQuestion() != null) {
                questionFromDatabase.setQuestion(question.getQuestion());
            }
            if (question.getCategory() != null) {
                questionFromDatabase.setCategory(question.getCategory());
            }
            return Optional.of(questionRepository.save(questionFromDatabase));
        } else {
            return Optional.empty();
        }

    }
}
