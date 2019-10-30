package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.Section;
import edu.northeastern.cs5200.fall2019.Models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends CrudRepository<Section, Integer> {
    @Query(value = "SELECT section FROM Section section WHERE section.title=:title")
    public Section findByTitle(@Param("title") String title);
}
