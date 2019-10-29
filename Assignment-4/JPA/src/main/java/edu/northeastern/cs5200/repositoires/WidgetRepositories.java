package edu.northeastern.cs5200.repositoires;

import edu.northeastern.cs5200.models.Widget;
import org.springframework.data.repository.CrudRepository;

public interface WidgetRepositories extends CrudRepository<Widget, Integer> {
}
