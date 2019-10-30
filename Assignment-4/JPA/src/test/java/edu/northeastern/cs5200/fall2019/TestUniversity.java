package edu.northeastern.cs5200.fall2019;


import edu.northeastern.cs5200.fall2019.DAOs.UniversityFinderDao;
import edu.northeastern.cs5200.fall2019.DAOs.UniversityUpdateDao;


import edu.northeastern.cs5200.fall2019.Models.Course;
import edu.northeastern.cs5200.fall2019.Models.Faculty;
import edu.northeastern.cs5200.fall2019.Models.Section;
import edu.northeastern.cs5200.fall2019.Models.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUniversity {
    @Autowired
    private UniversityUpdateDao universityUpdateDao;
    @Autowired
    private UniversityFinderDao universityFinderDao;


    @Test
    public void testTruncateDatabase(){
        universityUpdateDao.truncateDatabase();
    }

    @Test
    public void testCreateFaculty(){
        Faculty Alan = new Faculty(null, null, "Alan", "Turin", "123A", true, null, null);
        universityUpdateDao.createFaculty(Alan);
        Faculty Ada = new Faculty(null, null, "Ada", "Lovelace", "123B", true, null, null);
        universityUpdateDao.createFaculty(Ada);
    }

    @Test
    public void testCreateStudent(){
        Student Alice = new Student(null, null, "Alice", "Wonderland", 2020, 12000, null, null);
        universityUpdateDao.createStudent(Alice);
        Student Bob = new Student(null, null, "Bob", "Hope", 2021, 23000, null, null);
        universityUpdateDao.createStudent(Bob);
        Student Charlie = new Student(null, null, "Charlie", "Brown", 2019, 21000, null, null);
        universityUpdateDao.createStudent(Charlie);
        Student Dan = new Student(null, null, "Dan", "Craig", 2019, 0, null, null);
        universityUpdateDao.createStudent(Dan);
        Student Edward = new Student(null, null, "Edward", "Scissorhands", 2022, 11000, null, null);
        universityUpdateDao.createStudent(Edward);
        Student Frank = new Student(null, null, "Frank", "Herbert", 2018, 0, null, null);
        universityUpdateDao.createStudent(Frank);
        Student Gregory = new Student(null, null, "Gregory", "Peck", 2023, 10000, null, null);
        universityUpdateDao.createStudent(Gregory);
    }

    @Test
    public void testCreateCourse(){
        Faculty Alan = universityFinderDao.findFacultyByFirstName("Alan");
        Faculty Ada = universityFinderDao.findFacultyByFirstName("Ada");
        Course CS1234 = new Course("CS1234", null, null, Alan);
        universityUpdateDao.createCourse(CS1234);
        Course CS2345 = new Course("CS2345", null, null, Alan);
        universityUpdateDao.createCourse(CS2345);
        Course CS3456 = new Course("CS3456", null, null, Ada);
        universityUpdateDao.createCourse(CS3456);
        Course CS4567 = new Course("CS4567", null, null, Ada);
        universityUpdateDao.createCourse(CS4567);
    }

    @Test
    public void testCreateSection(){
        Course CS1234 = universityFinderDao.findCourseByTitle("CS1234");
        Course CS2345 = universityFinderDao.findCourseByTitle("CS2345");
        Course CS3456 = universityFinderDao.findCourseByTitle("CS3456");
        Section SEC4321 = new Section("SEC4321", 50, CS1234, null, null);
        universityUpdateDao.createSection(SEC4321);
        Section SEC5432 = new Section("SEC5432", 50, CS1234, null, null);
        universityUpdateDao.createSection(SEC5432);
        Section SEC6543 = new Section("SEC6543",50,  CS2345, null, null);
        universityUpdateDao.createSection(SEC6543);
        Section SEC7654 = new Section("SEC7654",50,  CS3456, null, null);
        universityUpdateDao.createSection(SEC7654);
    }

    @Test
    public void testEnrollStudentInSection(){
        Student Alice = universityFinderDao.findStudentByFirstName("Alice");
        Section SEC4321 = universityFinderDao.findSectionByTitle("SEC4321");
        Section SEC5432 = universityFinderDao.findSectionByTitle("SEC5432");
        universityUpdateDao.enrollStudentInSection(Alice, SEC4321);
        universityUpdateDao.enrollStudentInSection(Alice, SEC5432);
        Student Bob = universityFinderDao.findStudentByFirstName("Bob");
        universityUpdateDao.enrollStudentInSection(Bob, SEC5432);
        Student Charlie = universityFinderDao.findStudentByFirstName("Charlie");
        Section SEC6543 = universityFinderDao.findSectionByTitle("SEC6543");
        universityUpdateDao.enrollStudentInSection(Charlie, SEC6543);
    }

}
