package de.neuefische.quizdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public Question createNewQuestion(@RequestBody Question question){
        return questionService.createNewQuestion(question);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable String id){
        return ResponseEntity.of(questionService.findById(id));
    }

    @GetMapping("/search")
    public List<Question> findQuestionByCategory(@RequestParam String category){
        return questionService.findByCategory(category);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable String id, @RequestBody Question question){
        questionService.updateQuestion(id, question);
        return questionService.updateQuestion(id, question);
    }

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> patchQuestion(@PathVariable String id, @RequestBody Question question){
        return ResponseEntity.of(questionService.patchQuestion(id, question));
    }
}
