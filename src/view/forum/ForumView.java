/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forum;

import view.forum.ThreadView;
import controller.MainController;
import entity.DiscussionThread;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jirka_
 */
public class ForumView extends GridPane {
    
    private final double width = 500;
    private final double height = 500;
    
    private Stage primaryStage;
    private Scene scene;
    
    private Button newThreadButton = new Button("Nové diskuzní vlákno");
    
    private MainController controller;
    
    private ArrayList<DiscussionThread> threadCache;
    
    
    public ForumView(MainController controller) {
        this.controller = controller;
        
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
        
        init();
    }
    

    public void init() {
        this.getChildren().clear();
        
        ArrayList<DiscussionThread> discussionThreadsList;
        if (threadCache == null) {
            discussionThreadsList = controller.getForum().getDiscussionThreadsList();
        } else {
            discussionThreadsList = threadCache;
        }
        
        
        if (controller.isCurrentUserAdmin()) {
            this.add(newThreadButton, 0, 0, 3, 1);
        }
        
        add(new Label("Datum"), 0, 2);
        add(new Label("Název tématu"), 1, 2);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setStyle("-fx-background-color: white; -fx-fill: white");
        
        add(separator, 0, 3, 3, 1);
        
        int i = 4;
        for (DiscussionThread thread : discussionThreadsList) {
            String dateString = new Date(thread.getCreatedAt().getTime()).toLocaleString();
            Label dateLabel = new Label(dateString);
            Label threadLabel = new Label(thread.getTitle());
            
            Button btn = new Button("Přejít do diskuze");
            
            btn.setOnAction(e -> {
                controller.getMainAppView().threadView(thread);
            });
            
            add(dateLabel, 0, i);
            add(threadLabel, 1, i);
            add(btn, 2, i);
        };
        
        
        newThreadButton.setOnAction(e -> {
            controller.getMainAppView().newThreadView();
        });
        
        
        
        
        
    }

    public ArrayList<DiscussionThread> getThreadCache() {
        return threadCache;
    }

    public void setThreadCache(ArrayList<DiscussionThread> threadCache) {
        this.threadCache = threadCache;
    }
    
    
}
