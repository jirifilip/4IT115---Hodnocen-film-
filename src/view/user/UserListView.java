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
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class UserListView extends VBox {
    
    private MainController controller;
    
    public UserListView(MainController controller) {
        this.controller = controller;
    
        init();
    }
    
    public void init() {
        this.getChildren().clear();
        
        ArrayList<User> users = User.fetchAll();
        
        users.forEach(user -> {
            FlowPane userDetailContainer = new FlowPane();
            
            Button btn =  new Button(user.getUsername());
            
            btn.setOnAction(e -> {
                controller.getMainAppView().userDetailView(user);
            });
            
            userDetailContainer.getChildren().addAll(
                   btn
            );
            
            this.getChildren().add(userDetailContainer);
        });
        
    }
    
}
