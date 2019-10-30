package edu.northeastern.cs5200.fall2019.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends User {
    @Column(name = "GRAD_YEAR")
    private int gradYear;
    @Column(name = "SCHOLARSHIP")
    private long scholarship;
    @OneToMany(mappedBy = "student")
    private List<Answer> studentsAnswers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ENROLLMENT",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SECTION_ID", referencedColumnName = "ID"))
    private List<Section> enrolledSections;

    public Student() {
    }

    public Student(int gradYear, long scholarship, List<Answer> studentsAnswers, List<Section> enrolledSections) {
        this.gradYear = gradYear;
        this.scholarship = scholarship;
        this.studentsAnswers = studentsAnswers;
        this.enrolledSections = enrolledSections;
    }

    public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship, List<Answer> studentsAnswers, List<Section> enrolledSections) {
        super(username, password, firstName, lastName);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
        this.studentsAnswers = studentsAnswers;
        this.enrolledSections = enrolledSections;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }

    public List<Section> getEnrolledSections() {
        return enrolledSections;
    }

    public void setEnrolledSections(List<Section> enrolledSections) {
        this.enrolledSections = enrolledSections;
    }

    public List<Answer> getStudentsAnswers() {
        return studentsAnswers;
    }

    public void setStudentsAnswers(List<Answer> studentsAnswers) {
        this.studentsAnswers = studentsAnswers;
    }
}
