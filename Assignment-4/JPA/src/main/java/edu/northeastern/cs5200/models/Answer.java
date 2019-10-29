package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Question question;
    private boolean trueFalseAnswer;
    private int multipleChoiceAnswer;


    public Answer() {
    }

    public Answer(Student student, Question question, boolean trueFalseAnswer, int multipleChoiceAnswer) {
        this.student = student;
        this.question = question;
        this.trueFalseAnswer = trueFalseAnswer;
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isTrueFalseAnswer() {
        return trueFalseAnswer;
    }

    public void setTrueFalseAnswer(boolean trueFalseAnswer) {
        this.trueFalseAnswer = trueFalseAnswer;
    }

    public int getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public void setMultipleChoiceAnswer(int multipleChoiceAnswer) {
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }
}
