package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
    @Query(value = "SELECT faculty FROM Faculty faculty WHERE faculty.firstName=:firstName")
    public Faculty findByFirstName(@Param("firstName") String firstName);
}
