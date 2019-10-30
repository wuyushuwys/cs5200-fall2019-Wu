package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.ListWidget;
import org.springframework.data.repository.CrudRepository;

public interface ListWidgetRepository extends CrudRepository<ListWidget, Integer> {
    void deleteAll();

}
