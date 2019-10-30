package edu.northeastern.cs5200.fall2019.Repositoires;

import edu.northeastern.cs5200.fall2019.Models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query(value = "SELECT student FROM Student student WHERE student.firstName=:firstName")
    public Student findByFirstName(@Param("firstName") String firstName);
}
