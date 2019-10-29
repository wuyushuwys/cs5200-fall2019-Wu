package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
    @OneToMany(mappedBy = "moduleCourse")
    private List<Module> listOfModule;
    @OneToMany(mappedBy = "sectionCourse")
    private List<Section> listOfSection;
    @ManyToOne
    private Faculty faculty;

    public Course() {
    }

    public Course(String label, List<Module> listOfModule, List<Section> listOfSection, Faculty faculty) {
        this.label = label;
        this.listOfModule = listOfModule;
        this.listOfSection = listOfSection;
        this.faculty = faculty;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
