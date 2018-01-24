/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forum;

import component.NumberTextField;
import controller.MainController;
import entity.DiscussionThread;
import entity.MovieSite;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jirka_
 */
public class ThreadAddView extends GridPane {
    
    private MainController controller;
    
    private TextField titleField = new TextField();
    
    private Label titleLabel = new Label("Název stránky");
    
    
    private Button addMovieSiteButton = new Button("Ulož stránku");
    
    
    public ThreadAddView(MainController controller) {
        this.controller = controller;
        
        init();
    }
    
    
    
    
    public void init() {
        this.getChildren().clear();
        
        setAlignment(Pos.TOP_CENTER);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        add(titleLabel, 0, 0);
        add(titleField, 1, 0);
        
        add(addMovieSiteButton, 1, 1);
        
        addMovieSiteButton.setOnAction(e -> {
            String name = titleField.getText();
            
            new DiscussionThread(name);
            
            controller.getMainAppView().onForumButtonClick(new ActionEvent());
       });
    }
    
}
