package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao implements RoleImpl {
    private static RoleDao instance = null;
    private RoleDao() {};

    public static RoleDao getInstance() {
        if(instance == null)
            instance = new RoleDao();
        return instance;
    }

    private final String FIND_PAGE_ROLE = "SELECT * FROM page_role WHERE developer_id=? AND page_id=?";
    private final String ASSIGN_WEBSITE_ROLE = "INSERT INTO website_role(developer_id, website_id, role) VALUES (?,?,?) ";
    private final String ASSIGN_PAGE_ROLE = "INSERT INTO page_role(developer_id, page_id, role) VALUES (?,?,?) ";
    private final String DELETE_WEBSITE_ROLE = "DELETE FROM website_role WHERE developer_id=? AND website_id=? AND role=? ";
    private final String DELETE_PAGE_ROLE = "DELETE FROM page_role WHERE developer_id=? AND page_id=? AND role=? ";


    @Override
    public Role findPageRoleForDeveloper(int developerId, int pageId) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PAGE_ROLE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, pageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String role = resultSet.getString("role");
                return new Role(developerId, pageId, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void assignWebsiteRole(int developerId, int websiteId, String role) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_WEBSITE_ROLE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, websiteId);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignPageRole(int developerId, int pageId, String role) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_PAGE_ROLE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, pageId);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWebsiteRole(int developerId, int websiteId, String role) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, websiteId);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePageRole(int developerId, int pageId, String role) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAGE_ROLE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, pageId);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
