package com.QuizApp.Quiz_Service.Controller;


import com.QuizApp.Quiz_Service.DAO.QuestionDAO;
import com.QuizApp.Quiz_Service.DAO.QuizDTO;
import com.QuizApp.Quiz_Service.Models.Response;
import com.QuizApp.Quiz_Service.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        try{
            quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumberOfQuestion(),quizDTO.getTitle());
            return ResponseEntity.status(HttpStatus.CREATED).body("Quiz has been created");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not create the quiz");
    }

    @GetMapping(path = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionDAO>> getQuizQuestions(@PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuizQuestions(id));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());

    }

    @PostMapping(path = "/submit/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculateScore(@PathVariable Integer id,@RequestBody List<Response> response){
         try{
             Integer score=quizService.calculateResult(id,response);
             return ResponseEntity.status(HttpStatus.OK).body(score);
         }
         catch (Exception e){
             e.printStackTrace();
         }
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
