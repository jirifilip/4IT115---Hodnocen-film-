/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.DiscussionForum;
import entity.DiscussionThread;
import entity.User;
import javafx.stage.Stage;
import view.ForumView;
import view.LoginView;
import view.MainAppView;
import view.RegisterView;

/**
 *
 * @author Jirka_
 */
public class MainController {
    
    private User currentUser;
    private DiscussionForum forum = new DiscussionForum();
    private Stage primaryStage;

    

    private MainAppView mainAppView;
    
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
    }
    
    public void start() {
        
    }
    
    public void loginView() {
        new LoginView(this);
    }
    
    public void registerView() {
        new RegisterView(this);
    }
    
    public void forumView() {
    }
    
    public void profileView() {
        
    }
    
    public void threadView(DiscussionThread thread) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void mainView() {
        this.mainAppView = new MainAppView(this);
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
    
    public MainAppView getMainAppView() {
        return mainAppView;
    }

    public void setMainAppView(MainAppView mainAppView) {
        this.mainAppView = mainAppView;
    }

    

    
    
    
    
    
}
