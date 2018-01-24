/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.moviesite;

import component.NumberTextField;
import controller.MainController;
import entity.MovieSite;
import entity.User;
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
public class MovieSiteDetailView extends GridPane {
    
    private MainController controller;
    
    private Label titleField = new Label();
    private TextArea descriptionField = new TextArea();
    private Label urlField = new Label();
    private Label addIntensityBox = new Label();
    private CheckBox includesMoviesBox = new CheckBox();
    private CheckBox includesShowsBox = new CheckBox();
    private CheckBox requiresLoginBox = new CheckBox();
    
    private Label titleLabel = new Label("Název stránky");
    private Label descLabel = new Label("Popis stránky");
    private Label urlLabel = new Label("URL stránky");
    private Label addLabel = new Label("Intenzita reklam");
    private Label moviesLabel = new Label("Obsahuje filmy");
    private Label showsLabel = new Label("Obsahuje seriály");
    private Label loginLabel = new Label("Vyžaduje přihlášení");
    
    
    private Button editMovieSiteButton = new Button("Uprav stránku");
    
    
    public MovieSiteDetailView(MainController controller) {
        this.controller = controller;
        
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

    }
    
    
    
    
    public void init(MovieSite moviePage) {
        getChildren().clear();
        
        
        titleField.setText(moviePage.getName());
        descriptionField.setEditable(false);
        descriptionField.setText(moviePage.getDescription());
        urlField.setText(moviePage.getUrl());
        addIntensityBox.setText(String.valueOf(moviePage.getAddsIntensity()));
        includesMoviesBox.setSelected(moviePage.getContainsMovies());
        includesMoviesBox.setDisable(true);
        includesShowsBox.setSelected(moviePage.getContainsTvShows());
        includesShowsBox.setDisable(true);
        requiresLoginBox.setSelected(moviePage.getRequiresSignIn());
        requiresLoginBox.setDisable(true);
            
        this.add(titleLabel, 0, 0);
        this.add(titleField, 1, 0);
        
        this.add(descLabel, 0, 1);
        this.add(descriptionField, 1, 1);
        
        this.add(urlLabel, 0, 2);
        this.add(urlField, 1, 2);
        
        this.add(addLabel, 0, 3);
        this.add(addIntensityBox, 1, 3);
        
        this.add(moviesLabel, 0, 4);
        this.add(includesMoviesBox, 1, 4);
        
        this.add(showsLabel, 0, 5);
        this.add(includesShowsBox, 1, 5);
        
        this.add(loginLabel, 0, 6);
        this.add(requiresLoginBox, 1, 6);
        
        this.add(editMovieSiteButton, 1, 7);
        
        
        editMovieSiteButton.setOnAction(e -> {
            controller.getMainAppView().moviePageEditView(moviePage);
        });
        
    }

}