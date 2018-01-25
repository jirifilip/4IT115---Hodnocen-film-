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
import javafx.scene.control.TextArea;
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
        
        nameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        descriptionField.setEditable(false);
        descriptionField.setText(user.getProfileDescription());
        descriptionField.setMaxWidth(250);
        
        adminBox.setDisable(true);
        adminBox.setSelected(user.isAdmin());

        this.add(nameLabel, 0, 0);
        this.add(nameField, 1, 0);
        
        this.add(emailLabel, 0, 1);
        this.add(emailField, 1, 1);
        
        this.add(descLabel, 0, 2);
        this.add(descriptionField, 1, 2);
        
        this.add(adminLabel, 0, 3);
        this.add(adminBox, 1, 3);
        
        User currentUser = controller.getCurrentUser();
        
        if (controller.isCurrentUserAdmin()) {
            this.add(deleteUserButton, 1, 4);
            this.add(makeAdminButton, 1, 5);
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
