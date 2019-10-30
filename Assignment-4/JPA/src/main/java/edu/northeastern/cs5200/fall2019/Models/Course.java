package edu.northeastern.cs5200.fall2019.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @OneToMany(mappedBy = "course")
    private List<Module> listOfModule;
    @OneToMany(mappedBy = "course")
    private List<Section> listOfSection;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Faculty author;

    public Course() {
    }

    public Course(String title, List<Module> listOfModule, List<Section> listOfSection, Faculty author) {
        this.title = title;
        this.listOfModule = listOfModule;
        this.listOfSection = listOfSection;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Module> getListOfModule() {
        return listOfModule;
    }

    public void setListOfModule(List<Module> listOfModule) {
        this.listOfModule = listOfModule;
    }

    public List<Section> getListOfSection() {
        return listOfSection;
    }

    public void setListOfSection(List<Section> listOfSection) {
        this.listOfSection = listOfSection;
    }

    public Faculty getAuthor() {
        return author;
    }

    public void setAuthor(Faculty author) {
        this.author = author;
    }
}
