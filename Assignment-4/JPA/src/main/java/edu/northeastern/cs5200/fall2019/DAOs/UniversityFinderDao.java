package edu.northeastern.cs5200.fall2019.DAOs;

import edu.northeastern.cs5200.fall2019.Models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityFinderDao {

    public List<User> findAllUsers();

    public List<Faculty> findAllFaculty();

    public List<Student> findAllStudents();

    public List<Course> findAllCourses();

    public List<Section> findAllSections();

    public List<Course> findCoursesForAuthor(Faculty faculty);

    public List<Section> findSectionForCourse(Course course);

    public List<Student> findStudentsInSection(Section section);

    public List<Section> findSectionsForStudent(Student student);

    public Faculty findFacultyByFirstName(String firstName);

    public Course findCourseByTitle(String title);

    public Student findStudentByFirstName(String firstName);

    public Section findSectionByTitle(String title);

}
