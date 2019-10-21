package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Website;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WebsiteDao implements WebsiteImpl {
    private static WebsiteDao instance = null;
    private WebsiteDao() {};

    public static WebsiteDao getInstance() {
        if(instance == null)
            instance = new WebsiteDao();
        return instance;
    }

    private final String CREATE_WEBSITE_FOR_DEVELOPER = "INSERT INTO website(id, name, description, created, updated, visits, developer_id) " +
                                                        "VALUE(?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL_WEBSITE = "SELECT * FROM website";
    private final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM website WHERE developer_id = ?";
    private final String FIND_WEBSITES_BY_ID = "SELECT * FROM website WHERE id = ?";
    private final String UPDATE_WEBSITE = "UPDATE website SET name=?, description=?, created=?, updated=?, visits=?, developer_id=? WHERE id = ?";
    private final String DELETE_WEBSITE = "DELETE FROM website where id=?";

    @Override
    public void createWebsiteForDeveloper(int developerId, Website website) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_WEBSITE_FOR_DEVELOPER);
            statement.setInt(1, website.getId());
            statement.setString(2, website.getName());
            statement.setString(3, website.getDescription());
            statement.setDate(4, website.getCreated());
            statement.setDate(5, website.getUpdated());
            statement.setInt(6, website.getVisits());
            statement.setInt(7, developerId);
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Website> findAllWebsite() {
        Collection<Website> websites = new ArrayList<Website>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_WEBSITE);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");
                int visits = resultSet.getInt("visits");
                int developer_id = resultSet.getInt("developer_id");
                Website website = new Website(id, name, description, created, updated, visits);
                website.setDeveloper_id(developer_id);
                websites.add(website);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
    }

    @Override
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Collection<Website> websites = new ArrayList<Website>();
        ResultSet resultSet = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
            statement.setInt(1, developerId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");
                int visits = resultSet.getInt("visits");
                int developer_id = resultSet.getInt("developer_id");
                Website website = new Website(id, name, description, created, updated, visits);
                website.setDeveloper_id(developer_id);
                websites.add(website);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
    }

    @Override
    public Website findWebsiteById(int WebsiteId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_WEBSITES_BY_ID);
            statement.setInt(1, WebsiteId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");
                int visits = resultSet.getInt("visits");
                int developer_id = resultSet.getInt("developer_id");
                Website website = new Website(id, name, description, created, updated, visits);
                website.setDeveloper_id(developer_id);
                return website;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateWebsite(int websiteId, Website website) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_WEBSITE);
            statement.setString(1, website.getName());
            statement.setString(2, website.getDescription());
            statement.setDate(3, website.getCreated());
            statement.setDate(4, website.getUpdated());
            statement.setInt(5, website.getVisits());
            statement.setInt(6, website.getDeveloper_id());
            statement.setInt(7, websiteId);
            return statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteWebsite(int websiteId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_WEBSITE);
            statement.setInt(1, websiteId);
            return statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
