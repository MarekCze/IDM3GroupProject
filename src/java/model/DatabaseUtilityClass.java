package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DatabaseUtilityClass {

    public static Connection getConnection() {

        try {

            String host = "localhost";
            String dbName = "lit_art";
            int port = 3306;

            String mySqlUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

            Properties userInfo = new Properties();
            userInfo.put("user", "root");
            userInfo.put("password", "");

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
            return connection;
        } catch (Exception ex) {
            System.out.println("error on db connection" + ex.getMessage());
        }
        return null;
    }
}
