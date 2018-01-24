/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.moviesite;

import component.NumberTextField;
import controller.MainController;
import entity.MovieSite;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jirka_
 */
public class MovieSiteAddView extends GridPane {

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
    
    
    private Button addMovieSiteButton = new Button("Ulož stránku");
    
    
    public MovieSiteAddView(MainController controller) {
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
        
        
        add(addMovieSiteButton, 1, 7);
        
        addMovieSiteButton.setOnAction(e -> {
            String name = titleField.getText();
            String desc = descriptionField.getText();
            String url = urlField.getText();
            
            String addText = addIntensityBox.getText();
            
            int addIntensity = Integer.parseInt(addText.isEmpty() ? "0" : addText );
            boolean movies = includesMoviesBox.isSelected();
            boolean shows = includesShowsBox.isSelected();
            boolean login = requiresLoginBox.isSelected();
            
            new MovieSite(name, desc, url, addIntensity, movies, shows, login);
            
            controller.getMainAppView().onMovieSitesClick(new ActionEvent());
       });
    }
    
}
