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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jirka_
 */
public class UserDetailView extends ScrollPane {

    private MainController controller;
    
    private GridPane grid = new GridPane();
    
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
        
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

    }
    
    
    
    
    public void init(User user) {
        setContent(null);
        grid.getChildren().clear();
        
        profileImageView = new ImageView(user.getProfileImage(300, 300));
        
        nameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        descriptionField.setEditable(false);
        descriptionField.setText(user.getProfileDescription());
        descriptionField.setMaxWidth(250);
        
        adminBox.setDisable(true);
        adminBox.setSelected(user.isAdmin());
        
        grid.add(profileImageView, 1, 0);

        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        
        grid.add(descLabel, 0, 3);
        grid.add(descriptionField, 1, 3);
        
        grid.add(adminLabel, 0, 4);
        grid.add(adminBox, 1, 4);
        
        User currentUser = controller.getCurrentUser();
        
        if (controller.isCurrentUserAdmin()) {
            grid.add(deleteUserButton, 1, 5);
            grid.add(makeAdminButton, 1, 6);
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
        
        setContent(grid);
    }
    
}
