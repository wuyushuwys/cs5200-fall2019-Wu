package edu.northeastern.cs5200.models;


import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "QuizWidgets")
public class QuizWidget extends Widget {
    @OneToMany(mappedBy = "quizWidget")
    private List<Question> listOfQuestions;

    public QuizWidget() {
    }

    public QuizWidget(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }
}
