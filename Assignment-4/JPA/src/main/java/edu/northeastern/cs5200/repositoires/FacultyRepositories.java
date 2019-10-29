package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepositories extends CrudRepository<Faculty, Integer> {
}
