package com.QuizApp.Question_Service.Controller;
import com.QuizApp.Question_Service.DAO.QuestionDAO;
import com.QuizApp.Question_Service.Models.Question;
import com.QuizApp.Question_Service.Models.Response;
import com.QuizApp.Question_Service.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(path="/allQuestions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questions=questionService.getAllQuestions();
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questions);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());

    }

    @GetMapping(path = "/category/{category}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionService.findByCategory(category));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());


    }

    @PostMapping(path = "/upload/all",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadQuestions(@RequestBody List<Question> questions){
        try{
            questionService.uploadQuestions(questions);
            return ResponseEntity.status(HttpStatus.CREATED).body("All questions have been uploaded!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not add the question!!");
    }

    @PostMapping(path = "/upload",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadQuestions(@RequestBody Question questions){
        try{
            questionService.upload(questions);
            return ResponseEntity.status(HttpStatus.CREATED).body("Question has been uploaded");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not add the question!!");
    }

    // we will only be returning the id of the questions
    @GetMapping(path = "/generate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numQ){
        List<Integer> QuestionIDs=questionService.getQuestionsForQuiz(category,numQ);
        return ResponseEntity.status(HttpStatus.CREATED).body(QuestionIDs);
    }
    // another method where the quiz service can search for a particular question
    @PostMapping(path = "/getQuestions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionDAO>> fetchQuestion(@RequestBody List<Integer> ids){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsById(ids));
    }

    // for getting the score
    @PostMapping(path = "/get/score",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        Integer score=questionService.getUserScore(responses);
        return ResponseEntity.status(HttpStatus.OK).body(score);
    }

}
