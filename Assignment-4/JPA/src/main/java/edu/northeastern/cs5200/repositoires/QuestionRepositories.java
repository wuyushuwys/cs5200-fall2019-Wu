package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepositories extends CrudRepository<Question, Integer> {
}
