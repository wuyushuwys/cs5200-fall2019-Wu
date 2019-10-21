package com.example.cs5200_fall2019_wu_yushu_jdbc;

import edu.northeastern.cs5200.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	Statement statement = null;
	ResultSet results = null;
	{
		try {
			Connection connection = DatabaseConnection.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM Developer";
			results = statement.executeQuery(sql);
			while(results.next()){
				String id = results.getString("id");
				String first_name = results.getString("first_name");
				System.out.println(id+' '+first_name);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{

		}
	}
}
