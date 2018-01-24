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
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Jirka_
 */
public class MovieSiteListView extends GridPane {
    
    private MainController controller;
    private Button addMoviePageButton = new Button("Přidej novou filmovou stranu");
    
    public MovieSiteListView(MainController controller) {
        this.controller = controller;
    
        addMoviePageButton.setOnAction(e -> controller.getMainAppView().onNewMoviePageClick(e));
        
        init();
    }
    
    public void init() {
        setAlignment(Pos.TOP_CENTER);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(15, 15, 15, 15));
        
        
        this.getChildren().clear();
        
        
        ArrayList<MovieSite> movies = MovieSite.fetchAll();
        
        int i = 0;
        
        for (MovieSite movie : movies) {
            FlowPane movieDetailContainer = new FlowPane();
            
            Button btn =  new Button(movie.getName());
            
            Text pageName = new Text(movie.getName());
            Text urlText = new Text(movie.getUrl());
            Text addIntensityText = new Text(String.valueOf(movie.getAddsIntensity()));
            
            pageName.setOnMouseClicked(e -> {
                controller.getMainAppView().moviePageDetailView(movie);
            });
            
            add(pageName, 0, i);
            add(urlText, 1, i);
            add(addIntensityText, 2, i);
            
            i++;
        };
        
        add(addMoviePageButton, 0, i, 3, 1);
        
        
        
    }
    
}
