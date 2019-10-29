package edu.northeastern.cs5200.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
    @OneToMany(mappedBy = "lesson")
    private List<Topic> listOfTopics;
    @ManyToOne
    private Module module;

    public Lesson() {
    }

    public Lesson(String label, List<Topic> listOfTopics, Module module) {
        this.label = label;
        this.listOfTopics = listOfTopics;
        this.module = module;
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

    public List<Topic> getListOfTopics() {
        return listOfTopics;
    }

    public void setListOfTopics(List<Topic> listOfTopics) {
        this.listOfTopics = listOfTopics;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
