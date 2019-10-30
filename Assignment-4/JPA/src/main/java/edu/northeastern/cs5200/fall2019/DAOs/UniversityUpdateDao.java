package edu.northeastern.cs5200.fall2019.DAOs;

import edu.northeastern.cs5200.fall2019.Models.Course;
import edu.northeastern.cs5200.fall2019.Models.Faculty;
import edu.northeastern.cs5200.fall2019.Models.Section;
import edu.northeastern.cs5200.fall2019.Models.Student;



public interface UniversityUpdateDao {

//    Update Methods

//    removes all the data from the database.
//    Note that you might need to remove records in a particular order

    void truncateDatabase();

    Faculty createFaculty(Faculty faculty);

    Student createStudent(Student student);

    Course createCourse(Course course);

    Section createSection(Section section);

    Course addSectionToCourse(Section section, Course course);

    Course setAuthorForCourse(Faculty faculty, Course course);

    //    enrolls a student in a section updating the number of seats available and returning true.
//    If the current available seats is zero then the enrollment is refused and method returns false
    Boolean enrollStudentInSection(Student student, Section section);



}
