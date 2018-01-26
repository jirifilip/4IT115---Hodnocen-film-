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
import java.io.File;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * UI třída pro pohled na editaci filmu
 * 
 * @author Jirka_
 */
public class MovieSiteEditView extends GridPane {
    
    private MainController controller;
    
    private TextField titleField = new TextField();
    private TextArea descriptionField = new TextArea();
    private TextField urlField = new TextField();
    private NumberTextField addIntensityBox = new NumberTextField();
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
    
    private Label pictureNameLabel = new Label("[...]");
    
    private FileChooser fileChooser = new FileChooser();
    private Button openFileButton = new Button("Zvol profilovou fotku...");
    private File profilePicture;
    
    
    private Button saveMovieSiteButton = new Button("Uprav stránku");
    private Button deleteMovieSiteButton = new Button("Smaž stránku");
    
    /**
     * Konstruktor pohledu
     * @param controller
     */
    public MovieSiteEditView(MainController controller) {
        this.controller = controller;
        
        setAlignment(Pos.TOP_CENTER);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25, 25, 25, 25));

    }
    
    /**
     * Metoda pro inicializaci pohledu pro konkrétní stránku
     * @param movieSite
     */
    public void init(MovieSite movieSite) {
        getChildren().clear();
        
        
        titleField.setText(movieSite.getName());
        
        descriptionField.setMaxWidth(250);
        descriptionField.setText(movieSite.getDescription());
        urlField.setText(movieSite.getUrl());
        addIntensityBox.setText(String.valueOf(movieSite.getAddsIntensity()));
        includesMoviesBox.setSelected(movieSite.getContainsMovies());
        includesShowsBox.setSelected(movieSite.getContainsTvShows());
        requiresLoginBox.setSelected(movieSite.getRequiresSignIn());
        
        add(titleLabel, 0, 0);
        add(titleField, 1, 0);
        
        add(descLabel, 0, 1);
        add(descriptionField, 1, 1);
        
        add(urlLabel, 0, 2);
        add(urlField, 1, 2);
        
        add(addLabel, 0, 3);
        add(addIntensityBox, 1, 3);
        
        add(moviesLabel, 0, 4);
        add(includesMoviesBox, 1, 4);
        
        add(showsLabel, 0, 5);
        add(includesShowsBox, 1, 5);
        
        add(loginLabel, 0, 6);
        add(requiresLoginBox, 1, 6);
        
        add(pictureNameLabel, 0, 7);
        add(openFileButton, 1, 7);
        
        add(saveMovieSiteButton, 1, 8);
        
        User user = controller.getCurrentUser();
        if (user != null) {
            if (user.isAdmin()) {
                add(deleteMovieSiteButton, 1, 8);
            }
        }
        
        deleteMovieSiteButton.setOnAction(e -> {
            movieSite.delete();
            
            controller.getMainAppView().onMovieSitesClick(new ActionEvent());
        });
        
        openFileButton.setOnAction(e -> {
            profilePicture = fileChooser.showOpenDialog(new Stage());
            if (profilePicture != null) {
                pictureNameLabel.setText(profilePicture.getName());
            }
        });
        
        saveMovieSiteButton.setOnAction(e -> {
            String name = titleField.getText();
            String desc = descriptionField.getText();
            String url = urlField.getText();
            
            String addText = addIntensityBox.getText();
            
            int addIntensity = Integer.parseInt(addText.isEmpty() ? "0" : addText );
            boolean movies = includesMoviesBox.isSelected();
            boolean shows = includesShowsBox.isSelected();
            boolean login = requiresLoginBox.isSelected();
            
            
            movieSite.setAll(name, desc, url, addIntensity, movies, shows, login);
            movieSite.update();
            
            controller.getMainAppView().onMovieSitesClick(new ActionEvent());
       });
    }
    
}
