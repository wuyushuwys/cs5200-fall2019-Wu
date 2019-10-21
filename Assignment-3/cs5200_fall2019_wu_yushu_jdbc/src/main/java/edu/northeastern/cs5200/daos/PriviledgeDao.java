package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PriviledgeDao implements PriviledgeImpl {
    private static PriviledgeDao instance = null;
    private PriviledgeDao(){};

    public static PriviledgeDao getInstance(){
        if(instance == null)
            instance = new PriviledgeDao();
        return instance;
    }

    private final String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO website_priviledge(devloper_id, website_id, previledge) VALUES(?, ?, ?)";
    private final String ASSIGN_PAGE_PRIVILEDGE = "INSERT INTO page_priviledge(devloper_id, page_id, previledge) VALUES(?, ?, ?)";
    private final String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM website_priviledge WHERE devloper_id=? AND website_id=?  previledge=?)";
    private final String DELETE_PAGE_PRIVILEDGE = "DELETE FROM page_priviledge WHERE devloper_id=? AND page_id=?  previledge=?)";


    @Override
    public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, websiteId);
            preparedStatement.setString(3, priviledge);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, pageId);
            preparedStatement.setString(3, priviledge);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, websiteId);
            preparedStatement.setString(3, priviledge);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEDGE);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, pageId);
            preparedStatement.setString(3, priviledge);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
