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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class UserListView extends ScrollPane {
    
    private MainController controller;
    
    private ArrayList<User> usersChache;
    
    private GridPane grid;
    
    public UserListView(MainController controller) {
        this.controller = controller;
        
        grid = new GridPane();
    
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        init();
    }
    
    public void init() {
        this.setContent(null);
        
        grid.getChildren().clear();
        
        ArrayList<User> users;
        
        if (usersChache == null) {
            users = User.fetchAll();
        } else {
            users = usersChache;
        }
        
        
        
        grid.add(new Label("Uživatelské jméno"), 0, 0);
        grid.add(new Label("Email"), 1, 0);
        grid.add(new Label("Admin"), 2, 0);
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setStyle("-fx-background-color: white; -fx-fill: white");
        
        grid.add(separator, 0, 1, 4, 1);
        
        int i = 2;
        for (User user : users) {
            

            ImageView image = new ImageView(user.getProfileImage(50, 50));
            Label usernameLabel = new Label(user.getUsername());
            
            CheckBox adminBox = new CheckBox();
            
            adminBox.setSelected(user.isAdmin());
            adminBox.setDisable(true);
            
            Button btn = new Button("Přejít na profil");
            
            grid.add(image, 0, i);
            grid.add(usernameLabel, 1, i);
            grid.add(adminBox, 2, i);
            grid.add(btn, 3, i);
            
            btn.setOnAction(e -> {
                controller.getMainAppView().userDetailView(user);
            });
            
            
            i++;
        }
        
        
        this.setContent(grid);
    }
    
    public ArrayList<User> getUsersCache() {
        return usersChache;
    }

    public void setUsersCache(ArrayList<User> usersCache) {
        this.usersChache = usersCache;
    }
    
}
