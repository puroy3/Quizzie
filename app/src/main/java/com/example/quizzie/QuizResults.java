package com.example.quizzie;

public class QuizResults {
    private String questionNumber;
    private String userAnswer;
    private String correctAnswer;
    public QuizResults(String questionNumber, String userAnswer, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
    }
    public String getQuestionNumber() {
        return questionNumber;
    }
    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
    public String getUserAnswer() {
        return userAnswer;
    }
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
