/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.moviesite;

import controller.MainController;
import entity.DiscussionComment;
import entity.MovieSite;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class MovieSiteListView extends VBox {
    
    private MainController controller;
    private Button addMoviePageButton = new Button("Přidej novou filmovou stranu");
    
    public MovieSiteListView(MainController controller) {
        this.controller = controller;
    
        addMoviePageButton.setOnAction(e -> controller.getMainAppView().onNewMoviePageClick(e));
        
        init();
    }
    
    public void init() {
        this.getChildren().clear();
        
        getChildren().add(addMoviePageButton);
        
        ArrayList<MovieSite> movies = MovieSite.fetchAll();
        
        movies.forEach(movie -> {
            FlowPane movieDetailContainer = new FlowPane();
            
            Button btn =  new Button(movie.getName());
            
            btn.setOnAction(e -> {
                controller.getMainAppView().moviePageDetailView(movie);
            });
            
            movieDetailContainer.getChildren().addAll(
                   btn
            );
            
            this.getChildren().add(movieDetailContainer);
        });
        
    }
    
}
