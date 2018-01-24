/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jirka_
 */
public class MainAppView extends BorderPane {
    
    private final double width = 750;
    private final double height = 750;
    
    private Stage primaryStage;
    private Scene scene;
    
    // komponenty
    
    
    
    private VBox leftVBox = new VBox();
    
    private Label usernameLabel = new Label("Username");
    
    private TextField searchTextField = new TextField();
    
    private Button sitesButton = new Button("Sites");
    private Button myProfileButton = new Button("My Profile");
    private Button forumButton = new Button("Forum");
    private Button logoutButton = new Button("Log out");   
    
    private ProfileDetailView profileView;
    private ForumView forumView;
    private ThreadView threadView;
    private MovieListView movieListView;
    private AddMovieSiteView addMovieSiteView;
    
    private MainController controller;
    
    public MainAppView(MainController controller) {
        this.controller = controller;
        
        profileView = new ProfileDetailView(controller);
        forumView = new ForumView(controller);
        threadView = new ThreadView(controller);
        movieListView = new MovieListView(controller);
        addMovieSiteView = new AddMovieSiteView(controller);
        
        scene = new Scene(this, width, height);
        this.primaryStage = controller.getPrimaryStage();
        
        init();
    }

    private void init() {
        
        leftVBox.getChildren().addAll(usernameLabel, searchTextField, sitesButton,
                myProfileButton, forumButton, logoutButton);
        
        setLeft(leftVBox);
        setCenter(profileView);
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        forumButton.setOnAction(this::onForumButtonClick);
        myProfileButton.setOnAction(this::onProfileButtonClick);
        logoutButton.setOnAction(this::onLogoutClick);
        sitesButton.setOnAction(this::onMovieSitesClick);
    }
    
    public void onForumButtonClick(ActionEvent event) {
        forumView.init();
        setCenter(forumView);
    }
    
    public void onProfileButtonClick(ActionEvent event) {
        setCenter(profileView);
    }
    
    public void onMovieSitesClick(ActionEvent event) {
        movieListView.init();
        setCenter(movieListView);
    }
    
    public void onNewMoviePageClick(ActionEvent event) {
        setCenter(addMovieSiteView);
    }

    public ThreadView getThreadView() {
        return threadView;
    }

    public void setThreadView(ThreadView threadView) {
        this.threadView = threadView;
    }
    
    public void onLogoutClick(ActionEvent event) {
        controller.setCurrentUser(null);
        controller.loginView();
    }
    
    
    
}
