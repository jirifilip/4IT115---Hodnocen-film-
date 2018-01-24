/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import controller.MainController;
import entity.MovieSite;
import entity.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class ProfileSettingView extends GridPane {
    
    private MainController controller;
    
    private Label nameField = new Label();
    private Label emailField = new Label();
    private TextArea descriptionField = new TextArea();
    private PasswordField newPasswordField = new PasswordField();
    private PasswordField confirmPasswordField = new PasswordField();
    
    private CheckBox adminBox = new CheckBox();
    
    private Label nameLabel = new Label("Uživatelské jméno");
    private Label emailLabel = new Label("Email");
    private Label descLabel = new Label("Popis");
    private Label adminLabel = new Label("Admin");
    private Label passwordLabel = new Label("Nové heslo");
    private Label confirmPasswordLabel = new Label("Potvrďte heslo");
    
    
    private Button saveButton = new Button("Uložit nastavení");
    
    public ProfileSettingView(MainController controller) {
        this.controller = controller;
        
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
    }

    public void init(User user) {
        System.out.println("blabla");
        getChildren().clear();
        
        nameField.setText(user.getUsername());
        emailField.setText(user.getEmail());

        descriptionField.setText(user.getProfileDescription());
        descriptionField.setMaxWidth(250);
        
        this.add(nameLabel, 0, 0);
        this.add(nameField, 1, 0);
        
        this.add(emailLabel, 0, 1);
        this.add(emailField, 1, 1);
        
        this.add(descLabel, 0, 2);
        this.add(descriptionField, 1, 2);
        
        this.add(passwordLabel, 0, 3);
        this.add(newPasswordField, 1, 3);
        
        this.add(confirmPasswordLabel, 0, 4);
        this.add(confirmPasswordField, 1, 4);
        
        if (controller.getCurrentUser().isAdmin()) {
            adminBox.setSelected(user.isAdmin());
        
            this.add(adminLabel, 0, 5);
            this.add(adminBox, 1, 5);
        }
        
        this.add(saveButton, 1, 6);
        
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            user.setUsername(name);
            
            String desc = descriptionField.getText();
            user.setProfileDescription(desc);
            
            String email = emailField.getText();
            user.setEmail(email);
            
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            if (newPassword.isEmpty()) {
                if (newPassword.equals(confirmPassword)) {
                    user.setPassword(newPassword);
                }
            }
            
            if (!controller.getCurrentUser().isAdmin()) {
                if (adminBox.isSelected()) {
                    user.setAdmin(adminBox.isSelected());
                }
            }
            
            user.update();
            
            controller.getMainAppView().onUserListViewClick(e);
       });
    }
    
}
