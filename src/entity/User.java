/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

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
    private Timestamp registrationDate;
    private Timestamp lastLogin;
    
}
