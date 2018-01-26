/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Callback;
import utils.Crypto;

/**
 *
 * @author Jirka_
 */
public class Database {
    
    private static Database db;
    
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection =
            "jdbc:mysql://127.0.0.1:3306/4it115_movies?useUnicode=true&characterEncoding=UTF-8";
    private static final String dbUser = "root";
    private static final String dbPassword = "";
    
    private void Database() {
        
    };
    
    public static Database getInstance() {
        synchronized (new Object()) {
            if (db == null) {
                db = new Database();
            }

            return db;
        }
        
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbConnection, dbUser, dbPassword);
    }
    
    public User fetchUser(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;

        String hashedPassword = Crypto.hashSHA256(password);
        
        try {
//          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);

//          provedeni dotazu
            ResultSet rs = null;
            
            ArrayList<String> userParams = new ArrayList();
            userParams.add(username);
            userParams.add(hashedPassword);
            
            statement = connection.prepareStatement(
                    "SELECT * FROM user where username = ? and password = ?"
            );
            
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
                int id_ = rs.getInt(1);
                String username_ = rs.getString(2);
                String email_ = rs.getString(3);
                String password_ = rs.getString(4);
                String profileDesc = rs.getString(5);
                String profilePhoto = rs.getString(6);
                String registeredAt = rs.getString(7);
                String lastLogin = rs.getString(8);
                String admin = rs.getString(9);
                
                user = new User(id_, username_, email_, password_, profileDesc, profilePhoto, registeredAt, lastLogin, admin);
            }

            rs.close();
                

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public ResultSet executeGet(String parametr, Callback<ResultSet,Void> callback) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
//          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);

//          provedeni dotazu
            ResultSet rs = null;
            
            statement = connection.prepareStatement(parametr);
            
            rs = statement.executeQuery();

            callback.call(rs);

            rs.close();
                

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // delete, insert, update
    public void executeAction(String query, List<String> parameters) {
    
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);

            statement = connection.prepareStatement(query);
            
            int i = 1;
            for (String param : parameters) {
                statement.setString(i, param);
                i++;
            }

            statement.executeUpdate();
                
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }

        }
    
    
    
    } 
    
    
    
    
    public void executeQuery(String funkce, String parametr) {
        
        Connection connection = null;
        PreparedStatement statement = null;

        try {
//          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);

//          provedeni dotazu
            ResultSet rs = null;
            
            if (funkce.equals("GET")) {

                statement = connection.prepareStatement(parametr);

                rs = statement.executeQuery();
                
                System.out.println(rs);

                while (rs.next()) {
                    String title = rs.getString(2);
                    System.out.println(title);
                }
                
                rs.close();
                
            }
            
            statement.close();
            connection.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    } 
    
    
    
}
