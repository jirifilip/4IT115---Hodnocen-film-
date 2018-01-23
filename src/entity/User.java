/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;
import utils.Crypto;

/**
 *
 * @author Jirka_
 */
public class User extends Model {
    
    private int id;
    private String email;
    private String username;
    private String password;
    private String profileDescription;
    private String profilePhotoUrl;
    private String registrationDate;
    private String lastLogin;
    private String admin;

    public User(int id, String username, String email, String password, String profileDescription, String profilePhotoUrl, String registrationDate, String lastLogin, String admin) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileDescription = profileDescription;
        this.profilePhotoUrl = profilePhotoUrl;
        this.registrationDate = registrationDate;
        this.lastLogin = lastLogin;
        this.admin = admin;
    }
    
    

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = Crypto.hashSHA256(password);
        
        Date date = new Date();
        
        this.registrationDate = new Timestamp(date.getTime()).toString();
        
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add(this.username);
        paramList.add(this.email);
        paramList.add(this.password);
        paramList.add(this.registrationDate);
        
        Database
            .getInstance()
            .executeAction(
                    "insert into user(username, email, password, registered_at)"
                    + "values(?, ?, ?, ?)" , paramList);
    }
    
    public static User fetchUser(String username, String password) {
        return Database.getInstance().fetchUser(username, password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    
    
    
    
    
    
}
