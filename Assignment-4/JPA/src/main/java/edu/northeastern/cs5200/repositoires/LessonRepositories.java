package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepositories extends CrudRepository<Lesson, Integer> {
}
