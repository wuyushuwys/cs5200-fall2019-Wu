package edu.northeastern.cs5200.fall2019;

import edu.northeastern.cs5200.fall2019.DAOs.UniversityFinderDao;
import edu.northeastern.cs5200.fall2019.DAOs.UniversityUpdateDao;
import edu.northeastern.cs5200.fall2019.Models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateUniversity {
    @Autowired
    UniversityUpdateDao universityUpdateDao;
    @Autowired
    UniversityFinderDao universityFinderDao;

    @Test
    public void validateUsers(){
        List<User> users = universityFinderDao.findAllUsers();
        System.out.println("The number total of Users is " + users.size());
    }

    @Test
    public void validateFaculties(){
        List<Faculty> faculties = universityFinderDao.findAllFaculty();
        System.out.println("The number total of Faculties is " + faculties.size());
    }

    @Test
    public void validateStudents(){
        List<Student> students = universityFinderDao.findAllStudents();
        System.out.println("The number total of Students is " + students.size());
    }

    @Test
    public void validateCourses(){
        List<Course> courses = universityFinderDao.findAllCourses();
        System.out.println("The number total of Courses is " + courses.size());
    }

    @Test
    public void validateSections(){
        List<Section> sections = universityFinderDao.findAllSections();
        System.out.println("The number total of Sections is " + sections.size());
    }

    @Test
    public void validateCourseAuthorship(){
        List<Faculty> authors = universityFinderDao.findAllFaculty();
        for(Faculty author : authors) {
            List<Course> courses = universityFinderDao.findCoursesForAuthor(author);
            if(courses.isEmpty()){
                System.out.println(author.getFirstName() + " doesn't author any course");
            }
            else
                System.out.println(author.getFirstName() + " authors " + courses.size() + " courses");
        }
    }

    @Test
    public void validateSectionsPerCourse(){
        List<Course> courses = universityFinderDao.findAllCourses();
        for(Course course : courses){
            List<Section> sections = universityFinderDao.findSectionForCourse(course);
            if(sections.isEmpty())
                System.out.println(course.getTitle() + " does not have any section");
            else if(sections.size()==1)
                System.out.println(course.getTitle() + " has " + sections.size() + " section in total");
            else
                System.out.println(course.getTitle() + " has " + sections.size() + " sections in total");
        }
    }

    @Test
    public void validateSectionEnrollments(){
        List<Section> sections = universityFinderDao.findAllSections();
        for(Section section : sections){
            List<Student> students = universityFinderDao.findStudentsInSection(section);
            if(students.isEmpty())
                System.out.println(section.getTitle() + " does not have any student");
            else if(students.size()==1)
                System.out.println(section.getTitle() + " has " + sections.size() + " student in total");
            else
                System.out.println(section.getTitle() + " has " + sections.size() + " students in total");
        }
    }

    @Test
    public void validateStudentEnrollments(){
        List<Student> students = universityFinderDao.findAllStudents();
        for(Student student : students){
            List<Section> sections = universityFinderDao.findSectionsForStudent(student);
            if(sections.isEmpty())
                System.out.println(student.getFirstName() + " does not enroll any section");
            else if(sections.size()==1)
                System.out.println(student.getFirstName() + " enrolls " + sections.size() + " section in total");
            else
                System.out.println(student.getFirstName() + " enrolls " + sections.size() + " sections in total");
        }
    }

    @Test
    public void validateSectionSeats(){
        List<Section> sections = universityFinderDao.findAllSections();
        for(Section section : sections){
            if(section.getSeats()==0)
                System.out.println(section.getTitle() + " does not have any seat left");
            else if(section.getSeats()==1)
                System.out.println(section.getTitle() + " only has " + section.getSeats() + " seat left");
            else
                System.out.println(section.getTitle() + " has " + section.getSeats() + " seats left");
        }
    }

}
