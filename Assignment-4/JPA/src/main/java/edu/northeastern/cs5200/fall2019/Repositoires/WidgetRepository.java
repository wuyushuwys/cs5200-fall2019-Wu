package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.Widget;
import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
}
