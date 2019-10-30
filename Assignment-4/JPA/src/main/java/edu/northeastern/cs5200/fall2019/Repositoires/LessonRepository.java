package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
