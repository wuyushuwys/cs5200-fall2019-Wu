package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepositories extends CrudRepository<Topic, Integer> {
}
