package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PageDao implements PageImpl {
    private static PageDao instance = null;

    private PageDao() {}

    public static PageDao getInstance(){
        if(instance == null)
            instance = new PageDao();
        return instance;
    }

    private final String CREATE_PAGE_FOR_WEBSITE = "INSERT INTO page" +
            "(id, website_id, title, description, created, updated, views)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL_PAGES = "SELECT * FROM page";
    private final String FIND_PAGES_BY_ID = "SELECT * FROM page WHERE id=?";
    private final String FIND_PAGE_FOR_WEBSITE = "SELECT * FROM page WHERE website_id = ?";
    private final String UPDATE_PAGE = "UPDATE `page` " +
            "SET `website_id`=?, `title`=?, `description`=?, `created`=?, `updated`=?, `views`=? " +
            "WHERE `id`=?";
    private final String DELETE_PAGE = "DELETE FROM page WHERE id=?";

    @Override
    public void createPageForWebsite(int websiteId, Page page) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_PAGE_FOR_WEBSITE);
            statement.setInt(1, page.getId());
            statement.setInt(2, websiteId);
            statement.setString(3, page.getTitle());
            statement.setString(4, page.getDescription());
            statement.setDate(5, page.getCreated());
            statement.setDate(6, page.getUpdated());
            statement.setInt(7, page.getViews());
            statement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Page> findAllPages() {
        Collection<Page> pages = new ArrayList<Page>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_PAGES);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");
                int views = resultSet.getInt("views");
                int website_id = resultSet.getInt("website_id");
                Page page = new Page(id, title, description, created, updated, views);
                page.setWebsite_id(website_id);
                pages.add(page);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pages;
    }

    @Override
    public Page findPageById(int pageId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_PAGES_BY_ID);
            statement.setInt(1, pageId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");
                int views = resultSet.getInt("views");
                int website_id = resultSet.getInt("website_id");
                Page page = new Page(id, title, description, created, updated, views);
                page.setWebsite_id(website_id);
                return page;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Page> findPagesForWebsite(int websiteId) {
        Collection<Page> pages = new ArrayList<Page>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_PAGE_FOR_WEBSITE);
            statement.setInt(1, websiteId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int website_id = resultSet.getInt("website_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");
                int views = resultSet.getInt("views");
                Page page = new Page(id, title, description, created, updated, views);
                page.setWebsite_id(website_id);
                pages.add(page);
            }
            return pages;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updatePage(int pageId, Page page) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PAGE);
            statement.setInt(1, page.getWebsite_id());
            statement.setString(2, page.getTitle());
            statement.setString(3, page.getDescription());
            statement.setDate(4, page.getCreated());
            statement.setDate(5, page.getUpdated());
            statement.setInt(6, page.getViews());
            statement.setInt(7, pageId);
            return statement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deletePage(int pageId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_PAGE);
            statement.setInt(1, pageId);
            return statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
