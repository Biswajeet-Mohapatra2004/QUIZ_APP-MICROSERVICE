package com.QuizApp.Question_Service.Services;


import com.QuizApp.Question_Service.DAO.QuestionDAO;
import com.QuizApp.Question_Service.Models.Question;
import com.QuizApp.Question_Service.Models.Response;
import com.QuizApp.Question_Service.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private  QuestionRepository questionRepository;

    public Integer getUserScore(List<Response> responses) {
        int score=0;
        for(Response response:responses){
            Optional<Question> question= questionRepository.findById(response.getId());
            if (question.get().getRightAnswer().equals(response.getResponse())){
                score+=1;
            }
        }
        return score;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public void uploadQuestions(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public List<Question> findByCategory(String category) {
        return questionRepository.findQuestionByCategory(category);
    }

    public void upload(Question questions) {
        questionRepository.save(questions);
    }

    public List<Integer> getQuestionsForQuiz(String category, Integer numQ) {
        List<Integer> QuestionIDs=questionRepository.findRandomQuestionByCategory(category,numQ);
        return QuestionIDs;
    }

    public List<QuestionDAO> getQuestionsById(List<Integer> ids) {
         List<QuestionDAO> QuestionDAOs=new ArrayList<>();
         List<Question> questions=new ArrayList<>();

         for(Integer id:ids){
             questions.add(questionRepository.findById(id).get());
         }
         for(Question question:questions){
             QuestionDAO QuestionDAO=new QuestionDAO();
             QuestionDAO.setId(question.getId());
             QuestionDAO.setQuestionTitle(question.getQuestionTitle());
             QuestionDAO.setOption1(question.getOption1());
             QuestionDAO.setOption2(question.getOption2());
             QuestionDAO.setOption3(question.getOption3());
             QuestionDAO.setOption4(question.getOption4());

             QuestionDAOs.add(QuestionDAO);
         }
         return QuestionDAOs;

    }
}
