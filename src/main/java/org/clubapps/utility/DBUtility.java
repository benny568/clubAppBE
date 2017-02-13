package org.clubapps.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtility {
 private static Connection connection = null;

    public static Connection getConnection() 
    {

            try {
                Properties prop = new Properties();
                InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("application.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("spring.datasource.driver");
                String url = prop.getProperty("spring.datasource.url");
                String user = prop.getProperty("spring.datasource.user");
                String password = prop.getProperty("spring.datasource.password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }
    
    public static void closeConnection()
    {
    	try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}