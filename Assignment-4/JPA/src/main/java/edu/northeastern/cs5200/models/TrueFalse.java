package edu.northeastern.cs5200.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Table(name = "SINGLE_TRUE_FALSE_QUESTION")
public class TrueFalse extends Question {
    @Column(name = "IS_TRUE")
    private boolean isTrue;

    public TrueFalse() {
    }

    public TrueFalse(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public TrueFalse(int points, List<Answer> questionsAnswers, QuizWidget quizWidget, boolean isTrue) {
        super(points, questionsAnswers, quizWidget);
        this.isTrue = isTrue;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
