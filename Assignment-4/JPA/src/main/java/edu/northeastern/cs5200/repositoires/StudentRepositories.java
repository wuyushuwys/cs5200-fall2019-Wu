package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepositories extends CrudRepository<Student, Integer> {
}
