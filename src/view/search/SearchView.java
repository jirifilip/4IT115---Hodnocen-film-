/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.search;

import component.NumberTextField;
import controller.MainController;
import entity.DiscussionThread;
import entity.MovieSite;
import entity.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Jirka_
 */
public class SearchView extends GridPane {
    
    private MainController controller;
    
    private TextField searchMovieSitesField = new TextField();
    private TextField searchUsersField = new TextField();
    private TextField searchThreadsField = new TextField();

    private Button searchMovieSitesButton = new Button("Vyhledat filmovou stránku");
    private Button searchUsersButton = new Button("Vyhledat uživatele");
    private Button searchThreadsButton = new Button("Vyhledat diskuzní vlákno");

    
    public SearchView(MainController controller) {
        this.controller = controller;
        
        init();
    }
    
    
    public void init() {
        this.getChildren().clear();
        
        setAlignment(Pos.TOP_CENTER);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        Text txt = new Text("Vyhledávání");
        
        txt.setFont(Font.font("verdana", 20));
        txt.setFill(Paint.valueOf("white"));
        
        add(txt, 0, 0, 2, 1);
        
        add(searchMovieSitesField, 0, 1);
        add(searchMovieSitesButton, 1, 1);
        
        add(searchUsersField, 0, 2);
        add(searchUsersButton, 1, 2);
        
        add(searchThreadsField, 0, 3);
        add(searchThreadsButton, 1, 3);
        
        searchMovieSitesButton.setOnAction(e -> {
            controller.getMainAppView().getMovieListView().setMoviesCache(
                    MovieSite.searchFor(searchMovieSitesField.getText())
            );
            
            controller.getMainAppView().searchMovies();
            
            clearAll();
        });
        
        searchUsersButton.setOnAction(e -> {
            controller.getMainAppView().getUserListView().setUsersCache(
                    User.searchFor(searchUsersField.getText())
            );
            
            controller.getMainAppView().searchUsers();
            
            clearAll();
        });
        
        searchThreadsButton.setOnAction(e -> {
            controller.getMainAppView().getForumView().setThreadCache(
                    DiscussionThread.searchFor(searchThreadsField.getText())
            );
            
            controller.getMainAppView().searchThreads();
            
            clearAll();
        });
        
        
    }
    
    public void clearAll() {
        searchThreadsField.setText("");
        searchMovieSitesField.setText("");
        searchUsersField.setText("");
    }
    
}
