/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import view.forum.ForumView;
import view.forum.ThreadView;
import view.moviesite.MovieSiteDetailView;
import view.moviesite.MovieSiteListView;
import controller.MainController;
import entity.MovieSite;
import entity.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.moviesite.MovieSiteAddView;
import view.moviesite.MovieSiteEditView;
import view.user.ProfileSettingView;
import view.user.UserDetailView;
import view.user.UserListView;

/**
 *
 * @author Jirka_
 */
public class MainView extends BorderPane {
    
    private final double width = 750;
    private final double height = 750;
    
    private Stage primaryStage;
    private Scene scene;
    
    // komponenty
    
    
    
    private VBox leftVBox = new VBox();
    
    private Text usernameLabel = new Text("Nepřihlášený uživatel");
    
    private TextField searchTextField = new TextField();
    
    private Button sitesButton = new Button("Filmové stránky");
    private Button myProfileButton = new Button("Můj profil");
    private Button forumButton = new Button("Diskuzní fórum");
    private Button usersButton = new Button("Uživatelé");
    private Button logoutButton = new Button("Odhlásit se");   
    
    private UserListView userListView;
    private UserDetailView userDetailView;
    private ProfileSettingView profileView;
    
    private ForumView forumView;
    private ThreadView threadView;
    
    private MovieSiteListView movieListView;
    private MovieSiteAddView addMovieSiteView;
    private MovieSiteDetailView movieSiteDetailView;
    private MovieSiteEditView movieSiteEditView;
    
    
    
    private MainController controller;
    
    public MainView(MainController controller) {
        this.controller = controller;
        
        userDetailView = new UserDetailView(controller);
        userListView = new UserListView(controller);
        profileView = new ProfileSettingView(controller);
        
        forumView = new ForumView(controller);
        threadView = new ThreadView(controller);
        
        movieListView = new MovieSiteListView(controller);
        addMovieSiteView = new MovieSiteAddView(controller);
        movieSiteDetailView = new MovieSiteDetailView(controller);
        movieSiteEditView = new MovieSiteEditView(controller);
        
        scene = new Scene(this, width, height);
        this.primaryStage = controller.getPrimaryStage();
        
        init();
    }

    private void init() {
        
        leftVBox.getChildren().addAll(usernameLabel, sitesButton,
                myProfileButton, forumButton, usersButton, logoutButton);
        
        setLeft(leftVBox);
        setCenter(profileView);
        
        BorderPane.setMargin(leftVBox, new Insets(15, 15, 15, 15));
        BorderPane.setMargin(profileView, new Insets(15, 15, 15, 15));
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        forumButton.setOnAction(this::onForumButtonClick);
        myProfileButton.setOnAction(this::onProfileButtonClick);
        logoutButton.setOnAction(this::onLogoutClick);
        sitesButton.setOnAction(this::onMovieSitesClick);
        
        usersButton.setOnAction(this::onUserListViewClick);
        
        prepare();
    }
    
    public void prepare() {
        if (controller.getCurrentUser() == null) {
            logoutButton.setText("Zpět k přihlášení");
            leftVBox.getChildren().remove(myProfileButton);
        } else {
            usernameLabel.setText(controller.getCurrentUser().getUsername());
        }
        
        usernameLabel.setFont(Font.font("verdana", 14));
    }
    
    public void onForumButtonClick(ActionEvent event) {
        forumView.init();
        setCenter(forumView);
    }
    
    public void onProfileButtonClick(ActionEvent event) {
        setCenter(profileView);
    }
    
    public void onMovieSitesClick(ActionEvent event) {
        movieListView.init();
        setCenter(movieListView);
    }
    
    public void onNewMoviePageClick(ActionEvent event) {
        addMovieSiteView.init();
        setCenter(addMovieSiteView);
    }

    public ThreadView getThreadView() {
        return threadView;
    }

    public void setThreadView(ThreadView threadView) {
        this.threadView = threadView;
    }
    
    public void onLogoutClick(ActionEvent event) {
        controller.setCurrentUser(null);
        controller.loginView();
    }

    public void moviePageDetailView(MovieSite movie) {
        movieSiteDetailView.init(movie);
        setCenter(movieSiteDetailView);
    }
    
    public void moviePageEditView(MovieSite movieSite) {
        movieSiteEditView.init(movieSite);
        setCenter(movieSiteEditView);
    }

    public void userDetailView(User user) {
        userDetailView.init(user);
        setCenter(userDetailView);
    }
    
    public void onUserListViewClick(ActionEvent event) {
        userListView.init();
        setCenter(userListView);
    }

}

    
    
