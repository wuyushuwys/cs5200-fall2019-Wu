package edu.northeastern.cs5200.fall2019.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int seats;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Course course;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Faculty teacher;
    @ManyToMany(mappedBy = "enrolledSections", fetch = FetchType.LAZY)
    private List<Student> enrolledStudents;

    public Section() {
    }

    public Section(String title, int seats, Course course, Faculty teacher, List<Student> enrolledStudents) {
        this.title = title;
        this.seats = seats;
        this.course = course;
        this.teacher = teacher;
        this.enrolledStudents = enrolledStudents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Faculty getTeacher() {
        return teacher;
    }

    public void setTeacher(Faculty teacher) {
        this.teacher = teacher;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
