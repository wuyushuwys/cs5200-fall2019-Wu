package edu.northeastern.cs5200.fall2019.Models;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "faculties")
public class Faculty extends User {
    @Column(name = "OFFICE")
    private String office;
    @Column(name = "IS_TENURED")
    private boolean tenured;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Course> authoredCourses;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Section> taughtSections;

    public Faculty() {
    }

    public Faculty(String office, boolean tenured, List<Course> authoredCourses, List<Section> taughtSections) {
        this.office = office;
        this.tenured = tenured;
        this.authoredCourses = authoredCourses;
        this.taughtSections = taughtSections;
    }

    public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured, List<Course> authoredCourses, List<Section> taughtSections) {
        super(username, password, firstName, lastName);
        this.office = office;
        this.tenured = tenured;
        this.authoredCourses = authoredCourses;
        this.taughtSections = taughtSections;
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

    public List<Section> getTaughtSections() {
        return taughtSections;
    }

    public void setTaughtSections(List<Section> taughtSections) {
        this.taughtSections = taughtSections;
    }
}
