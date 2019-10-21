package edu.northeastern.cs5200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://cs5200-fall2019-wu.crtzxgcj28vw.us-east-2.rds.amazonaws.com:3306/JDBC";
    private static final String USER = "root";
    private static final String PASSWORD = "cs5200fall2019";
    private static java.sql.Connection dbConnection = null;


    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        if(dbConnection == null){
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            return dbConnection;
        }
        else{
            return dbConnection;
        }
    }
    public static void closeConnection(){
        try{
            if(dbConnection != null) {
                dbConnection.close();
                dbConnection = null;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
