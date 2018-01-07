/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jirka_
 */
public class LoginView extends VBox {

    private final double width = 500;
    private final double height = 500;
    
    private Stage primaryStage;
    private Scene scene;
    
    // komponenty
    private Label loginLabel = new Label("Login");
    private TextField emailTextField = new TextField();
    private TextField passwordTextField = new TextField();
    private Button loginButton = new Button("Login");
    private Button registerButton = new Button("Register");
    private Button lostPasswordButton = new Button("Lost Password");
    
    
    public LoginView(Stage primaryStage) {
        scene = new Scene(this, width, height);
        this.primaryStage = primaryStage;
        
        init();
    }

    private void init() {
        this.getChildren().addAll(loginLabel, emailTextField, passwordTextField,
                loginButton, registerButton, lostPasswordButton);
        
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
        loginButton.setOnAction(e -> new MainAppView(primaryStage));

    }
    
    
    
}
