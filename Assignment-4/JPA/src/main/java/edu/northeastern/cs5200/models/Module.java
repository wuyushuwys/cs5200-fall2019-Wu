package edu.northeastern.cs5200.models;


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
    private Course moduleCourse;

    @OneToMany(mappedBy = "module")
    private List<Lesson> listOfLesson;

    public Module() {
    }

    public Module(String label, Course moduleCourse, List<Lesson> listOfLesson) {
        this.label = label;
        this.moduleCourse = moduleCourse;
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

    public Course getModuleCourse() {
        return moduleCourse;
    }

    public void setModuleCourse(Course moduleCourse) {
        this.moduleCourse = moduleCourse;
    }

    public List<Lesson> getListOfLesson() {
        return listOfLesson;
    }

    public void setListOfLesson(List<Lesson> listOfLesson) {
        this.listOfLesson = listOfLesson;
    }
}
