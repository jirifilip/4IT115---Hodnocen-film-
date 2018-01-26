/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.DiscussionForum;
import entity.DiscussionThread;
import entity.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import view.forum.ForumView;
import view.login.LoginView;
import view.main.MainView;
import view.login.RegisterView;

/**
 *  Třída slouží jako hlavní controller aplikace.
 * 
 * @author Jirka_
 */
public class MainController {
    
    private User currentUser;
    private DiscussionForum forum = new DiscussionForum();
    private Stage primaryStage;

    

    private MainView mainAppView;
    
    
    /**
    * Metoda slouž pro zobrazení chybové hlášky. 
    */
    public static void alert(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
    
    /**
    * Konstruktor třídy.
    */
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
    }
    
    /**
    * Metoda slouží pro zjištění, zda aktuální uživatel je admin. 
    */
    public boolean isCurrentUserAdmin() {
        if (currentUser != null) {
            if (currentUser.isAdmin()) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
    * Metoda pro start kontroleru. 
    */
    public void start() {
        
    }
    
    /**
    * Metoda pro pohled login. 
    */
    public void loginView() {
        new LoginView(this);
    }
    
    /**
    * Metoda pro pohled registrace. 
    */
    public void registerView() {
        new RegisterView(this);
    }
    
    /**
    * Metoda pro hlavní pohled. 
    */
    public void mainView() {
        this.mainAppView = new MainView(this);
    }
    
    public DiscussionForum getForum() {
        return forum;
    }

    public void setForum(DiscussionForum forum) {
        this.forum = forum;
    }
    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public MainView getMainAppView() {
        return mainAppView;
    }

    public void setMainAppView(MainView mainAppView) {
        this.mainAppView = mainAppView;
    }


    

    
    
    
    
    
}
