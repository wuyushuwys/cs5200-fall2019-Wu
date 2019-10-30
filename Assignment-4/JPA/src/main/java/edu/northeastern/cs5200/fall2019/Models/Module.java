package edu.northeastern.cs5200.fall2019.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;

    @ManyToOne
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> listOfLesson;

    public Module() {
    }

    public Module(String label, Course course, List<Lesson> listOfLesson) {
        this.label = label;
        this.course = course;
        this.listOfLesson = listOfLesson;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Lesson> getListOfLesson() {
        return listOfLesson;
    }

    public void setListOfLesson(List<Lesson> listOfLesson) {
        this.listOfLesson = listOfLesson;
    }
}
