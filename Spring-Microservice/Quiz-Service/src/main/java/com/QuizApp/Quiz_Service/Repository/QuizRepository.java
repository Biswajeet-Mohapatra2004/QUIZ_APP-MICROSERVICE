package com.QuizApp.Quiz_Service.Repository;


import com.QuizApp.Quiz_Service.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
