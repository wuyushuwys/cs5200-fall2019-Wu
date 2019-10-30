package edu.northeastern.cs5200.fall2019.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tables")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
    @ManyToOne
    @JsonIgnore
    private Lesson lesson;
    @OneToMany(mappedBy = "topic")
    private List<Widget> listOfWidget;


    public Topic() {
    }

    public Topic(String label, Lesson lesson, List<Widget> listOfWidget) {
        this.label = label;
        this.lesson = lesson;
        this.listOfWidget = listOfWidget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Widget> getListOfWidget() {
        return listOfWidget;
    }

    public void setListOfWidget(List<Widget> listOfWidget) {
        this.listOfWidget = listOfWidget;
    }
}
