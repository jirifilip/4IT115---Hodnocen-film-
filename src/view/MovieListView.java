/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import entity.DiscussionComment;
import entity.MoviePage;
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
public class MovieListView extends VBox {
    
    private MainController controller;
    private Button addMoviePageButton = new Button("Přidej novou filmovou stranu");
    
    public MovieListView(MainController controller) {
        this.controller = controller;
    
        addMoviePageButton.setOnAction(e -> controller.getMainAppView().onNewMoviePageClick(e));
        
        init();
    }
    
    public void init() {
        this.getChildren().clear();
        
        getChildren().add(addMoviePageButton);
        
        ArrayList<MoviePage> movies = MoviePage.fetchAll();
        
        movies.forEach(movie -> {
            FlowPane movieDetailContainer = new FlowPane();
            
            Button btn =  new Button(movie.getName());
            
            movieDetailContainer.getChildren().addAll(
                   btn
            );
            
            this.getChildren().add(movieDetailContainer);
        });
        
    }
    
}
