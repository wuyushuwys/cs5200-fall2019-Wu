package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query(value = "SELECT course FROM Course course WHERE course.title=:title")
    public Course findCourseByTitle(@Param("title") String title);
}
