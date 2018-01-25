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
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author Jirka_
 */
public class MovieSiteListView extends GridPane {
    
    private MainController controller;
    private Button addMoviePageButton = new Button("Přidej novou filmovou stránku");
    
    private ArrayList<MovieSite> moviesCache;
    
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
        
        
        ArrayList<MovieSite> movies;
        if (moviesCache == null) {
            movies = MovieSite.fetchAll();
        } else {
            movies = moviesCache;
        }
        
        
        Text nameHeader = new Text("Jméno stránky");
        Text urlHeader = new Text("URL");
        Text ratingHeader = new Text("Hodnocení uživatelů");
        
        nameHeader.setFill(Paint.valueOf("white"));
        urlHeader.setFill(Paint.valueOf("white"));
        ratingHeader.setFill(Paint.valueOf("white"));
        
        add(nameHeader, 0, 0);
        add(urlHeader, 1, 0);
        add(ratingHeader, 2, 0);
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setStyle("-fx-background-color: white; -fx-fill: white");
        
        add(separator, 0, 1, 4, 1);
        
        int i = 2;
        
        for (MovieSite movie : movies) {
            FlowPane movieDetailContainer = new FlowPane();
            
            Button btn =  new Button(movie.getName());
            
            Text pageName = new Text(movie.getName());
            pageName.setFill(Paint.valueOf("white"));
            
            Text urlText = new Text(movie.getUrl());
            urlText.setFill(Paint.valueOf("white"));
            
            Text ratingText = new Text(String.valueOf(movie.getRating()));
            ratingText.setFill(Paint.valueOf("white"));
            
            Button goToPageButton = new Button("Přejít na detail");
            
            goToPageButton.setOnMouseClicked(e -> {
                controller.getMainAppView().moviePageDetailView(movie);
            });
            
            add(pageName, 0, i);
            add(urlText, 1, i);
            add(ratingText, 2, i);
            add(goToPageButton, 3, i);
                    
            
            i++;
        };
        
        add(addMoviePageButton, 0, i, 4, 1);
        
        
        
    }

    public void setMoviesCache(ArrayList<MovieSite> moviesCache) {
        this.moviesCache = moviesCache;
    }
    
    
    
    
}
