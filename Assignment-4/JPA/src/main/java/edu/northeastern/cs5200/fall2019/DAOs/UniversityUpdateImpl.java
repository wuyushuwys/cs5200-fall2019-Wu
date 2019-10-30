package edu.northeastern.cs5200.fall2019.DAOs;

import edu.northeastern.cs5200.fall2019.Models.Course;
import edu.northeastern.cs5200.fall2019.Models.Faculty;
import edu.northeastern.cs5200.fall2019.Models.Section;
import edu.northeastern.cs5200.fall2019.Models.Student;
import edu.northeastern.cs5200.fall2019.Repositoires.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UniversityUpdateImpl implements UniversityUpdateDao {

//    update Methods
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MultipleChoiceRepository multipleChoiceRepository;
    @Autowired
    TrueFalseRepository trueFalseRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizWidgetRepository quizWidgetRepository;
    @Autowired
    YouTubeWidgetRepository youTubeWidgetRepository;
    @Autowired
    ListWidgetRepository listWidgetRepository;
    @Autowired
    ParagraphWidgetRepository paragraphWidgetRepository;
    @Autowired
    HeadingWidgetRepository headingWidgetRepository;
    @Autowired
    ImageWidgetRepository imageWidgetRepository;
    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UserRepository userRepository;


//    removes all the data from the database.
//    Note that you might need to remove records in a particular order
    @Override
    public void truncateDatabase() {
        answerRepository.deleteAll();
//        multipleChoiceRepository.deleteAll();
//        trueFalseRepository.deleteAll();
        questionRepository.deleteAll();
//        quizWidgetRepository.deleteAll();
//        youTubeWidgetRepository.deleteAll();
//        listWidgetRepository.deleteAll();
//        paragraphWidgetRepository.deleteAll();
//        headingWidgetRepository.deleteAll();
//        imageWidgetRepository.deleteAll();
        widgetRepository.deleteAll();
        topicRepository.deleteAll();
        lessonRepository.deleteAll();
        moduleRepository.deleteAll();
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
//        facultyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        Optional<Faculty> optional = facultyRepository.findById(faculty.getId());
        if(optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public Student createStudent(Student student) {
        studentRepository.save(student);
        Optional<Student> optional = studentRepository.findById(student.getId());
        if(optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public Course createCourse(Course course) {
        courseRepository.save(course);
        Optional<Course> optional = courseRepository.findById(course.getId());
        if(optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public Section createSection(Section section) {
        sectionRepository.save(section);
        Optional<Section> optional = sectionRepository.findById(section.getId());
        if(optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public Course addSectionToCourse(Section section, Course course) {
        Optional<Course> optional = courseRepository.findById(course.getId());
        if(optional.isPresent()){
            List<Section> listOfSections = course.getListOfSection();
            listOfSections.add(section);
            course.setListOfSection(listOfSections);
            courseRepository.save(course);
            optional = courseRepository.findById(course.getId());
            if(optional.isPresent())
                return optional.get();
        }
        return null;
    }

    @Override
    public Course setAuthorForCourse(Faculty faculty, Course course) {
        course.setAuthor(faculty);
        courseRepository.save(course);
        Optional<Course> optional = courseRepository.findById(course.getId());
        if(optional.isPresent())
            return optional.get();
        return null;
    }

//    enrolls a student in a section updating the number of seats available and returning true.
//    If the current available seats is zero then the enrollment is refused and method returns false
    @Override
    public Boolean enrollStudentInSection(Student student, Section section) {
        Optional<Section> optionalSection = sectionRepository.findById(section.getId());
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if(optionalSection.isPresent() && optionalStudent.isPresent()){
            Section section1 = optionalSection.get();
            Student student1 = optionalStudent.get();
            int numberOfSeats = section1.getSeats();
            if(numberOfSeats > 0) {
                List<Student> listOfStudents = section1.getEnrolledStudents();
                List<Section> listOfSections = student1.getEnrolledSections();
                listOfStudents.add(student);
                listOfSections.add(section);
                numberOfSeats--;
                section1.setSeats(numberOfSeats);
                section1.setEnrolledStudents(listOfStudents);
                student1.setEnrolledSections(listOfSections);
                sectionRepository.save(section1);
                studentRepository.save(student1);
                return true;
            }
            else
                return false;
        }
        return null;
    }

}
