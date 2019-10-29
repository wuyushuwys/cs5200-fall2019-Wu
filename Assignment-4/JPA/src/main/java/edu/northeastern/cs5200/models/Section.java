package edu.northeastern.cs5200.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seats;
    @ManyToOne
    private Course sectionCourse;
    @ManyToMany(mappedBy = "enrolledSections")
    private List<Student> enrolledStudents;

    public Section() {
    }

    public Section(int seats, List<Student> enrolledStudents) {
        this.seats = seats;
        this.enrolledStudents = enrolledStudents;
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

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
