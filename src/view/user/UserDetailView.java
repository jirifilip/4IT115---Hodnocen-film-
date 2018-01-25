/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import controller.MainController;
import entity.MovieSite;
import entity.User;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jirka_
 */
public class UserDetailView extends GridPane {

    private MainController controller;
    
    private Label nameField = new Label();
    private Label emailField = new Label();
    private TextArea descriptionField = new TextArea();
    private CheckBox adminBox = new CheckBox();
    
    private ImageView profileImageView;
    
    private Label nameLabel = new Label("Uživatelské jméno");
    private Label emailLabel = new Label("Email");
    private Label descLabel = new Label("Popis");
    private Label adminLabel = new Label("Admin");
    
    private Button deleteUserButton = new Button("Smazat uživatele");
    private Button makeAdminButton = new Button("Povyš na admina");
    
    
    
    
    public UserDetailView(MainController controller) {
        this.controller = controller;
        
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

    }
    
    
    
    
    public void init(User user) {
        getChildren().clear();
        
        profileImageView = new ImageView(user.getProfileImage(300, 300));
        
        nameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        descriptionField.setEditable(false);
        descriptionField.setText(user.getProfileDescription());
        descriptionField.setMaxWidth(250);
        
        adminBox.setDisable(true);
        adminBox.setSelected(user.isAdmin());
        
        this.add(profileImageView, 1, 0);

        this.add(nameLabel, 0, 1);
        this.add(nameField, 1, 1);
        
        this.add(emailLabel, 0, 2);
        this.add(emailField, 1, 2);
        
        this.add(descLabel, 0, 3);
        this.add(descriptionField, 1, 3);
        
        this.add(adminLabel, 0, 4);
        this.add(adminBox, 1, 4);
        
        User currentUser = controller.getCurrentUser();
        
        if (controller.isCurrentUserAdmin()) {
            this.add(deleteUserButton, 1, 5);
            this.add(makeAdminButton, 1, 6);
        }
        
        deleteUserButton.setOnAction(e -> {
            user.delete();
            
            controller.getMainAppView().onUserListViewClick(new ActionEvent());
        });
        
        makeAdminButton.setOnAction(e -> {
            user.setAdmin(true);
            user.update();
            
            controller.alert("Informace", "", "Z uživatele " + user.getUsername() + " se stal admin.");
            
            controller.getMainAppView().onUserListViewClick(new ActionEvent());
        });
        
    }
    
}
