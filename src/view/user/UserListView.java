/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import controller.MainController;
import entity.MovieSite;
import entity.User;
import java.awt.Checkbox;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class UserListView extends GridPane {
    
    private MainController controller;
    
    public UserListView(MainController controller) {
        this.controller = controller;
    
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
        init();
    }
    
    public void init() {
        this.getChildren().clear();
        
        ArrayList<User> users = User.fetchAll();
        
        
        add(new Label("Uživatelské jméno"), 0, 0);
        add(new Label("Email"), 1, 0);
        add(new Label("Admin"), 2, 0);
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setStyle("-fx-background-color: white; -fx-fill: white");
        
        add(separator, 0, 1, 4, 1);
        
        int i = 2;
        for (User user : users) {
            FlowPane userDetailContainer = new FlowPane();
            
            Label usernameLabel = new Label(user.getUsername());
            Label emailLabel = new Label(user.getEmail());
            CheckBox adminBox = new CheckBox();
            
            adminBox.setSelected(user.isAdmin());
            adminBox.setDisable(true);
            
            Button btn = new Button("Přejít na profil");
            
            add(usernameLabel, 0, i);
            add(emailLabel, 1, i);
            add(adminBox, 2, i);
            add(btn, 3, i);
            
            btn.setOnAction(e -> {
                controller.getMainAppView().userDetailView(user);
            });
            
            
            i++;
        }
        
    }
    
}
