package edu.northeastern.cs5200.fall2019.DAOs;

import edu.northeastern.cs5200.fall2019.Models.*;
import edu.northeastern.cs5200.fall2019.Repositoires.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class UniversityFinderImpl implements UniversityFinderDao {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SectionRepository sectionRepository;


    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Faculty> findAllFaculty() {
        return (List<Faculty>)facultyRepository.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return (List<Student>)studentRepository.findAll();
    }

    @Override
    public List<Course> findAllCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public List<Section> findAllSections() {
        return (List<Section>)sectionRepository.findAll();
    }

    @Override
    public List<Course> findCoursesForAuthor(Faculty faculty) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty.getId());
        if(optionalFaculty.isPresent())
            return optionalFaculty.get().getAuthoredCourses();
        return null;
    }

    @Override
    public List<Section> findSectionForCourse(Course course) {
        Optional<Course>  optionalCourse = courseRepository.findById(course.getId());
        if(optionalCourse.isPresent())
            return optionalCourse.get().getListOfSection();
        return null;
    }

    @Override
    public List<Student> findStudentsInSection(Section section) {
        Optional<Section> optionalSection = sectionRepository.findById(section.getId());
        if(optionalSection.isPresent())
            return optionalSection.get().getEnrolledStudents();
        return null;
    }

    @Override
    public List<Section> findSectionsForStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if(optionalStudent.isPresent())
            return optionalStudent.get().getEnrolledSections();
        return null;
    }

    @Override
    public Faculty findFacultyByFirstName(String firstName) {
        return facultyRepository.findByFirstName(firstName);
    }

    @Override
    public Course findCourseByTitle(String title) {
        return courseRepository.findCourseByTitle(title);
    }

    @Override
    public Student findStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @Override
    public Section findSectionByTitle(String title) {
        return sectionRepository.findByTitle(title);
    }

}
