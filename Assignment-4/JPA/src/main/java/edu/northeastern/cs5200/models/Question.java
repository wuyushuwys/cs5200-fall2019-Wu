package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int points;
    @OneToMany(mappedBy = "question")
    private List<Answer> questionsAnswers;
    @ManyToOne
    private QuizWidget quizWidget;

    public Question() {
    }

    public Question(int points, List<Answer> questionsAnswers, QuizWidget quizWidget) {
        this.points = points;
        this.questionsAnswers = questionsAnswers;
        this.quizWidget = quizWidget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Answer> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(List<Answer> questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    public QuizWidget getQuizWidget() {
        return quizWidget;
    }

    public void setQuizWidget(QuizWidget quizWidget) {
        this.quizWidget = quizWidget;
    }
}
