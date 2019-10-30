package edu.northeastern.cs5200.fall2019.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Entity
//@Table(name = "SINGLE_MULTIPLE_CHOICE_QUESTION")
public class MultipleChoice extends Question {
    @Column(name = "CHOICES")
    private String choices;
    @Column(name = "CORRECT")
    private int correct;

    public MultipleChoice() {
    }

    public MultipleChoice(String choices, int correct) {
        this.choices = choices;
        this.correct = correct;
    }

    public MultipleChoice(int points, List<Answer> questionsAnswers, QuizWidget quizWidget, String choices, int correct) {
        super(points, questionsAnswers, quizWidget);
        this.choices = choices;
        this.correct = correct;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
