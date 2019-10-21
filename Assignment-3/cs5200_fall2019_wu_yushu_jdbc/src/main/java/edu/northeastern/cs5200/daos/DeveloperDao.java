package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DeveloperDao implements DeveloperImpl {
    private static DeveloperDao instance = null;
    private DeveloperDao(){}

    public static DeveloperDao getInstance() {
        if(instance == null)
            instance = new DeveloperDao();
        return instance;
    }

//    All SQL Command
    private final String INSERT_PERSON =
        "INSERT INTO person (id, first_name, last_name, username, password, email, dob, address, phone) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String INSERT_DEVELOPER = "INSERT INTO developer (id, developer_key) VALUES (?, ?)";

    private final String INSERT_USER = "INSERT INTO `user` (id, user_agreement) VALUES (?, ?)";
    private final String FIND_ALL_DEVELOPERS = "SELECT * FROM `developer`, `person` WHERE `developer`.`id`=`person`.`id`";
    private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM `developer`, `person` WHERE `developer`.`id`=`person`.`id` AND `developer`.`id`=?";
    private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM `developer`, `person` WHERE `developer`.`id`=`person`.`id` AND `person`.`username`=?";
    private final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM `developer`, `person` WHERE `developer`.id=`person`.`id` AND `person`.`username`=? AND `person`.`username`=?";
    private final String UPDATE_DEVELOPER = "UPDATE `person` INNER JOIN `developer` ON `person`.`id`=`developer`.`id`" +
                                        "SET `person`.`first_name`=? , `person`.`last_name`=? , `person`.`username`=? , " +
                                        "`person`.`password`=? , `person`.`email`=? , `person`.`dob`=? , `person`.`address`=? ," +
                                        "`person`.`phone`=? , `developer`.`developer_key`=? " +
                                        "WHERE `developer`.`id`=?";
    private static String DELETE_DEVELOPER = "DELETE FROM person WHERE id=?";

    public void createDeveloper(Developer developer) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statementPerson = connection.prepareStatement(INSERT_PERSON);
            PreparedStatement statementDeveloper = connection.prepareStatement(INSERT_DEVELOPER);
            statementPerson.setInt(1, developer.getId());
            statementPerson.setString(2, developer.getFirst_name());
            statementPerson.setString(3, developer.getLast_name());
            statementPerson.setString(4, developer.getUsername());
            statementPerson.setString(5, developer.getPassword());
            statementPerson.setString(6, developer.getEmail());
            statementPerson.setDate(7, developer.getDob());
            statementPerson.setString(8, developer.getAddress());
            statementPerson.setString(9, developer.getPhone());
            statementPerson.executeUpdate();
            statementDeveloper.setInt(1, developer.getId());
            statementDeveloper.setString(2, developer.getDeveloper_key());
            statementDeveloper.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createUser(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statementPerson = connection.prepareStatement(INSERT_PERSON);
            PreparedStatement statementUser = connection.prepareStatement(INSERT_USER);
            statementPerson.setInt(1, user.getId());
            statementPerson.setString(2, user.getFirst_name());
            statementPerson.setString(3, user.getLast_name());
            statementPerson.setString(4, user.getUsername());
            statementPerson.setString(5, user.getPassword());
            statementPerson.setString(6, user.getEmail());
            statementPerson.setDate(7, user.getDob());
            statementPerson.setString(8, user.getAddress());
            statementPerson.setString(9, user.getPhone());
            statementPerson.executeUpdate();
            statementUser.setInt(1, user.getId());
            statementUser.setBoolean(2, user.isUser_agreement());
            statementUser.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Collection<Developer> findAllDevelopers() {
        Collection<Developer> developers = new ArrayList<Developer>();
        ResultSet results = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            results = statement.executeQuery(FIND_ALL_DEVELOPERS);
            while(results.next()){
                int id = results.getInt("id");
                String first_name = results.getString("first_name");
                String last_name = results.getString("last_name");
                String username = results.getString("username");
                String password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String address = results.getString("address");
                String phone = results.getString("phone");
                String developer_key = results.getString("developer_key");
                Developer new_developer = new Developer(id, first_name, last_name, username, password, email, dob, address, phone, developer_key);
                developers.add(new_developer);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }
    public Developer findDeveloperById(int developerId) {
        Developer developer_by_id = null;
        ResultSet results = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
            statement.setInt(1, developerId);
            results = statement.executeQuery();
            while(results.next()) {
                int id = results.getInt("id");
                String first_name = results.getString("first_name");
                String last_name = results.getString("last_name");
                String username = results.getString("username");
                String password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String address = results.getString("address");
                String phone = results.getString("phone");
                String developer_key = results.getString("developer_key");
                return new Developer(id, first_name, last_name, username, password, email, dob, address, phone, developer_key);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Developer findDeveloperByUsername(String username) {
        Developer developer_by_username = null;
        ResultSet results = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
            statement.setString(1, username);
            results = statement.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                String first_name = results.getString("first_name");
                String last_name = results.getString("last_name");
                String _username = results.getString("username");
                String password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String address = results.getString("address");
                String phone = results.getString("phone");
                String developer_key = results.getString("developer_key");
                return new Developer(id, first_name, last_name, _username, password, email, dob, address, phone, developer_key);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer_by_username;
    }
    public Developer findDeveloperByCredentials(String username, String password) {
        Developer developer_by_credentials = null;
        ResultSet results = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
            statement.setString(1, username);
            statement.setString(2, password);
            results = statement.executeQuery();
            while(results.next()) {
                int id = results.getInt("id");
                String first_name = results.getString("first_name");
                String last_name = results.getString("last_name");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String address = results.getString("address");
                String phone = results.getString("phone");
                String developer_key = results.getString("developer_key");
                return new Developer(id, first_name, last_name, _username, _password, email, dob, address, phone, developer_key);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer_by_credentials;
    }
    public int updateDeveloper(int developerId, Developer developer){
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_DEVELOPER);

            statement.setString(1, developer.getFirst_name());
            statement.setString(2, developer.getLast_name());
            statement.setString(3, developer.getUsername());
            statement.setString(4, developer.getPassword());
            statement.setString(5, developer.getEmail());
            statement.setDate(6, developer.getDob());
            statement.setString(7, developer.getAddress());
            statement.setString(8, developer.getPhone());
            statement.setString(9, developer.getDeveloper_key());
            statement.setInt(10, developerId);
            return statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteDeveloper(int developerId){
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_DEVELOPER);
            statement.setInt(1, developerId);
            return statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
