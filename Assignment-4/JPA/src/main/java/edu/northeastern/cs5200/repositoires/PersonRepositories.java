package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepositories extends CrudRepository<Person, Integer> {
}
