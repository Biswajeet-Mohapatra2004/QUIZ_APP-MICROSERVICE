package com.QuizApp.Quiz_Service.Services;


import com.QuizApp.Quiz_Service.DAO.QuestionDAO;
import com.QuizApp.Quiz_Service.Feign.QuizInterface;
import com.QuizApp.Quiz_Service.Models.Quiz;
import com.QuizApp.Quiz_Service.Models.Response;
import com.QuizApp.Quiz_Service.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;


    public void createQuiz(String category, int numQ, String title) {
        List<Integer> questions=quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
    }

    public List<QuestionDAO> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz=quizRepository.findById(id);
       List<QuestionDAO> questions=quizInterface.fetchQuestion(quiz.get().getQuestions()).getBody();
       return questions;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses).getBody();
    }
}
