/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
    
    public MainAppView(Stage primaryStage) {
        scene = new Scene(this, width, height);
        this.primaryStage = primaryStage;
        
        init();
    }

    private void init() {
        
        leftVBox.getChildren().addAll(usernameLabel, searchTextField, sitesButton,
                myProfileButton, forumButton, logoutButton);
        
        setLeft(leftVBox);
        setCenter(new ProfileDetailView());
        
        primaryStage.setScene(scene);
        
        primaryStage.show();

    }
    
    
    
    
}
