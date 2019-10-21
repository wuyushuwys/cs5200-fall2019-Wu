package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.User;

import java.sql.SQLException;
import java.util.Collection;

public interface DeveloperImpl {

    void createDeveloper(Developer developer);

    void createUser(User user);

    Collection<Developer> findAllDevelopers() throws SQLException, ClassNotFoundException;

    Developer findDeveloperById(int developerId);

    Developer findDeveloperByUsername(String username);

    Developer findDeveloperByCredentials(String username, String password);

    int updateDeveloper(int developerId, Developer developer);

    int deleteDeveloper(int developerId);

}
