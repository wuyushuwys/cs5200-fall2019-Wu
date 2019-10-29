package edu.northeastern.cs5200.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Table(name = "faculties")
public class Faculty extends Person{
    @Column(name = "OFFICE")
    private String office;
    @Column(name = "IS_TENURED")
    private boolean tenured;
    @OneToMany(mappedBy = "faculty")
    private List<Course> authoredCourses;


    public Faculty() {
    }

    public Faculty(String office, boolean tenured) {
        this.office = office;
        this.tenured = tenured;
    }

    public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured, List<Course> authoredCourses) {
        super(username, password, firstName, lastName);
        this.office = office;
        this.tenured = tenured;
        this.authoredCourses = authoredCourses;
    }

    public boolean isTenured() {
        return tenured;
    }

    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public List<Course> getAuthoredCourses() {
        return authoredCourses;
    }

    public void setAuthoredCourses(List<Course> authoredCourses) {
        this.authoredCourses = authoredCourses;
    }
}
