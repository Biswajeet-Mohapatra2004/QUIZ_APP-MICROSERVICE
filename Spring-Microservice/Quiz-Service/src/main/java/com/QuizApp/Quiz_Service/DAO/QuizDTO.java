package com.QuizApp.Quiz_Service.DAO;

public class QuizDTO {
    private String category;
    private Integer numberOfQuestion;
    private String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "category='" + category + '\'' +
                ", numberOfQuestion=" + numberOfQuestion +
                ", title='" + title + '\'' +
                '}';
    }
}
