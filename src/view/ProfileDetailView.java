/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class ProfileDetailView extends VBox {

    private Label titleLabel = new Label("My profile");
    
    private Label emailLabel = new Label("Email");
    private Label usernameLabel = new Label("Username");
    
    private TextField emailTextField = new TextField();
    private TextField usernameTextField = new TextField();

    private Button changePasswordButton = new Button("Change password");
    
    private Button saveChangesButton = new Button("Save Changes");
    
    
    private MainController controller;
    
    
    public ProfileDetailView(MainController controller) {
        this.controller = controller;
        
        init();
    }

    public void init() {
        this.getChildren().addAll(titleLabel, emailLabel, emailTextField, usernameLabel,
                usernameTextField, changePasswordButton, saveChangesButton);
         
    }
    
}
