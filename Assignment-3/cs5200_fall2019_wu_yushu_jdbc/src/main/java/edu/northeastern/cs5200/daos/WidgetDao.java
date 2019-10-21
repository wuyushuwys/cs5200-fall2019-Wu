package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Widget;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WidgetDao implements WidgetImpl{
    private static WidgetDao instance = null;
    private WidgetDao(){}

    public static WidgetDao getInstance() {
        if(instance == null)
            instance = new WidgetDao();
        return instance;
    }

    private final String CREATE_WIDGET_FOR_PAGE = "INSERT INTO widget(`id`, `name`, `page_id`, `width`, `height`, `css_class`, `css_style`, " +
            "`text`, `order`, `heading_size`, `html`, `image_src`, `youtube_url`, `youtube_shareable`, `youtube_expandable`, `dtype`) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL_WIDGET = "SELECT * FROM widget";
    private final String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id = ?";
    private final String FIND_WIDGETS_FOR_PAGE = "SELECT * FROM widget WHERE page_id = ?";
    private final String UPDATE_WIDGET = "UPDATE `widget` SET `name`=?, `page_id`=?, `width`=?, `height`=?, `css_class`=?, `css_style`=?, " +
            "`text`=?, `order`=?, `heading_size`=?, `html`=?, `image_src`=?, `youtube_url`=?, `youtube_shareable`=?, `youtube_expandable`=? " +
            "WHERE `id`=?";
    private final String DELETE_WIDGET = "DELETE FROM widget WHERE id=?";

    @Override
    public void createWidgetForPage(int pageId, Widget widget) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_WIDGET_FOR_PAGE);
            preparedStatement.setInt(1, widget.getId());
            preparedStatement.setString(2, widget.getName());
            preparedStatement.setInt(3, pageId);
            preparedStatement.setInt(4, widget.getWidth());
            preparedStatement.setInt(5, widget.getHeight());
            preparedStatement.setString(6, widget.getCssClass());
            preparedStatement.setString(7, widget.getCssStyle());
            preparedStatement.setString(8, widget.getText());
            preparedStatement.setInt(9, widget.getOrder());
            preparedStatement.setInt(10, widget.getOrder());
            preparedStatement.setString(11, widget.getHtml());
            preparedStatement.setString(12, widget.getSrc());
            preparedStatement.setString(13, widget.getUrl());
            preparedStatement.setBoolean(14, widget.isShareable());
            preparedStatement.setBoolean(15, widget.isExpandable());
            preparedStatement.setString(16, widget.getType().toString());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Widget> findAllWidgets() {
        try {
            Collection<Widget> widgets = new ArrayList<Widget>();
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_WIDGET);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int page_id = resultSet.getInt("page_id");
                String name = resultSet.getString("name");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                String css_class = resultSet.getString("css_class");
                String css_style = resultSet.getString("css_style");
                String text = resultSet.getString("text");
                int order = resultSet.getInt("order");
                int heading_size = resultSet.getInt("heading_size");
                String html = resultSet.getString("html");
                String image_src = resultSet.getString("image_src");
                String youtube_url = resultSet.getString("youtube_url");
                Boolean youtube_shareable = resultSet.getBoolean("youtube_shareable");
                Boolean youtube_expandable = resultSet.getBoolean("youtube_expandable");
//                String  dtype = resultSet.getString("DTYPE");
                Widget.type type = Widget.type.valueOf(resultSet.getString("DTYPE"));
                Widget widget = new Widget(id, page_id, name, width, height, css_class, css_style, text, order, heading_size, html, image_src, youtube_url, youtube_shareable, youtube_expandable, type);
                widgets.add(widget);
            }
            return widgets;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Widget findWidgetById(int widgetId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_WIDGET_BY_ID);
            preparedStatement.setInt(1, widgetId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int page_id = resultSet.getInt("page_id");
                String name = resultSet.getString("name");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                String css_class = resultSet.getString("css_class");
                String css_style = resultSet.getString("css_style");
                String text = resultSet.getString("text");
                int order = resultSet.getInt("order");
                int heading_size = resultSet.getInt("heading_size");
                String html = resultSet.getString("html");
                String image_src = resultSet.getString("image_src");
                String youtube_url = resultSet.getString("youtube_url");
                Boolean youtube_shareable = resultSet.getBoolean("youtube_shareable");
                Boolean youtube_expandable = resultSet.getBoolean("youtube_expandable");
//                String  dtype = resultSet.getString("DTYPE");
                Widget.type type = Widget.type.valueOf(resultSet.getString("DTYPE"));
                Widget widget = new Widget(id, page_id, name, width, height, css_class, css_style, text, order, heading_size, html, image_src, youtube_url, youtube_shareable, youtube_expandable, type);
                return widget;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Widget> findWidgetsForPage(int pageId) {
        try {
            Collection<Widget> widgets = new ArrayList<Widget>();
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_WIDGETS_FOR_PAGE);
            preparedStatement.setInt(1, pageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int page_id = resultSet.getInt("page_id");
                String name = resultSet.getString("name");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                String css_class = resultSet.getString("css_class");
                String css_style = resultSet.getString("css_style");
                String text = resultSet.getString("text");
                int order = resultSet.getInt("order");
                int heading_size = resultSet.getInt("heading_size");
                String html = resultSet.getString("html");
                String image_src = resultSet.getString("image_src");
                String youtube_url = resultSet.getString("youtube_url");
                Boolean youtube_shareable = resultSet.getBoolean("youtube_shareable");
                Boolean youtube_expandable = resultSet.getBoolean("youtube_expandable");
//                String  dtype = resultSet.getString("DTYPE");
                Widget.type type = Widget.type.valueOf(resultSet.getString("DTYPE"));
                Widget widget = new Widget(id, page_id,  name, width, height, css_class, css_style, text, order, heading_size, html, image_src, youtube_url, youtube_shareable, youtube_expandable, type);
                widgets.add(widget);
            }
            return widgets;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateWidget(int widgetId, Widget widget) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WIDGET);
            preparedStatement.setString(1, widget.getName());
            preparedStatement.setInt(2, widget.getPage_id());
            preparedStatement.setInt(3, widget.getWidth());
            preparedStatement.setInt(4, widget.getHeight());
            preparedStatement.setString(5, widget.getCssClass());
            preparedStatement.setString(6, widget.getCssStyle());
            preparedStatement.setString(7, widget.getText());
            preparedStatement.setInt(8, widget.getOrder());
            preparedStatement.setInt(9, widget.getSize());
            preparedStatement.setString(10, widget.getHtml());
            preparedStatement.setString(11, widget.getSrc());
            preparedStatement.setString(12, widget.getUrl());
            preparedStatement.setBoolean(13, widget.isShareable());
            preparedStatement.setBoolean(14, widget.isExpandable());
            preparedStatement.setInt(15, widgetId);
            return preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteWidget(int widgetId) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WIDGET);
            preparedStatement.setInt(1, widgetId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
