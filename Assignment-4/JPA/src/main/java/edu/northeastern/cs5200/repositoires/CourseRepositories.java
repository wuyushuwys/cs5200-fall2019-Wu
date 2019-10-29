package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepositories extends CrudRepository<Course, Integer> {
}
