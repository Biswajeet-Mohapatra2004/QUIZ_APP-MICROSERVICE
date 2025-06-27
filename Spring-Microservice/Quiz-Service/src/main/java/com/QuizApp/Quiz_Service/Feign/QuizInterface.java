package com.QuizApp.Quiz_Service.Feign;

import com.QuizApp.Quiz_Service.DAO.QuestionDAO;
import com.QuizApp.Quiz_Service.Models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("Question-Service") // creating the feign client and mentioning the name of the service we are connecting to
public interface QuizInterface {
    // mention the services we are going to use or just define those methods here

    @GetMapping(path = "/question/generate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);


    @PostMapping(path = "/question/getQuestions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionDAO>> fetchQuestion(@RequestBody List<Integer> ids);


    @PostMapping(path = "/question/get/score",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

    // now to use this interface we are going to create its object
}
