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
import entity.DiscussionThread;
import entity.MovieSite;
import entity.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.forum.ThreadAddView;
import view.moviesite.MovieSiteAddView;
import view.moviesite.MovieSiteEditView;
import view.search.SearchView;
import view.user.ProfileSettingView;
import view.user.UserDetailView;
import view.user.UserListView;

/**
 * Třída pro hlavní okno aplikace - UI
 * @author Jirka_
 */
public class MainView extends BorderPane {
    
    private final double width = 950;
    private final double height = 550;
    
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
    private Button searchButton = new Button("Vyhledávání");
    
    private UserListView userListView;
    private UserDetailView userDetailView;
    private ProfileSettingView profileView;
    
    private ForumView forumView;
    private ThreadView threadView;
    private ThreadAddView threadAddView;
    
    private MovieSiteListView movieListView;
    private MovieSiteAddView addMovieSiteView;
    private MovieSiteDetailView movieSiteDetailView;
    private MovieSiteEditView movieSiteEditView;
    
    private SearchView searchView;
    
    
    
    private MainController controller;
    
    /**
     * Konstrukor pro hlavní pohled na aplikaci
     * @param controller
     */
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
        threadAddView = new ThreadAddView(controller);
        
        searchView = new SearchView(controller);
        
        scene = new Scene(this, width, height);
        scene.getStylesheets().add("style/main.css");
        this.primaryStage = controller.getPrimaryStage();
        
        init();
    }

    /**
     * Metoda pro inicializaci pohledu
     */
    public void init() {
        searchButton.setId("ipad-grey");
        sitesButton.setId("ipad-grey");
        myProfileButton.setId("ipad-grey");
        forumButton.setId("ipad-grey");
        usersButton.setId("ipad-grey");
        logoutButton.setId("ipad-grey");
        
        usernameLabel.setFont(Font.font("verdana", 26));
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setStyle("-fx-background-color: white; -fx-fill: white");
        
        leftVBox.getChildren().addAll(usernameLabel, separator, searchButton, 
                sitesButton, myProfileButton, forumButton, usersButton, logoutButton);
        
        setLeft(leftVBox);
        setCenter(profileView);
        
        leftVBox.setStyle("-fx-background-color: #14141f;");
        
//        BorderPane.setMargin(leftVBox, new Insets(15, 15, 15, 15));
        BorderPane.setMargin(profileView, new Insets(15, 15, 15, 15));
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        searchButton.setOnAction(this::onSearchClick);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        
        forumButton.setOnAction(this::onForumButtonClick);
        forumButton.setMaxWidth(Double.MAX_VALUE);
        
        myProfileButton.setOnAction(this::onProfileButtonClick);
        myProfileButton.setMaxWidth(Double.MAX_VALUE);
        
        logoutButton.setOnAction(this::onLogoutClick);
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        
        sitesButton.setOnAction(this::onMovieSitesClick);
        sitesButton.setMaxWidth(Double.MAX_VALUE);
        
        usersButton.setOnAction(this::onUserListViewClick);
        usersButton.setMaxWidth(Double.MAX_VALUE);
        
        prepare();
    }
    
    /**
     * Metoda pro připravení pohledu na základě oprávnění
     * používajícího uživatele
     */
    public void prepare() {
        if (controller.getCurrentUser() == null) {
            logoutButton.setText("Zpět k přihlášení");
            leftVBox.getChildren().remove(myProfileButton);
        } else {
            usernameLabel.setText(controller.getCurrentUser().getUsername());
        }
        
        usernameLabel.setFont(Font.font("verdana", 14));
        usernameLabel.setFill(Paint.valueOf("white"));
    }
    
    /**
     * Hander pro kliknutí na tlačítko fórum
     * @param event
     */
    public void onForumButtonClick(ActionEvent event) {
        forumView.setThreadCache(null);
        forumView.init();
        setCenter(forumView);
    }
    
    /**
     * Handler pro kliknutí na tlačítko profil
     * @param event
     */
    public void onProfileButtonClick(ActionEvent event) {
        profileView.init(controller.getCurrentUser());
        setCenter(profileView);
    }
    
    /**
     * Handler pro kliknutí na tlačítko filmových stránek
     * @param event
     */
    public void onMovieSitesClick(ActionEvent event) {
        movieListView.setMoviesCache(null);
        movieListView.init();
        setCenter(movieListView);
    }
    
    /**
     * Handler pro kliknutí na tlačítko vyhledávat
     * @param event
     */
    public void searchMovies() {
        movieListView.init();   
        movieListView.init();
        setCenter(movieListView);
    }
    
    /**
     * Handler pro kliknutí na tlačítko filmových stránek
     * @param event
     */
    public void onNewMoviePageClick(ActionEvent event) {
        addMovieSiteView.init();
        setCenter(addMovieSiteView);
    }
    
    /**
     * Handler pro kliknutí na tlačítko vyhledání
     * @param event
     */
    public void onSearchClick(ActionEvent event) {
        searchView.init();
        setCenter(searchView);
    }

    public ThreadView getThreadView() {
        return threadView;
    }

    public void setThreadView(ThreadView threadView) {
        this.threadView = threadView;
    }
    
    /**
     * Handler pro kliknutí na tlačítko odhlášení
     * @param event
     */
    public void onLogoutClick(ActionEvent event) {
        controller.setCurrentUser(null);
        controller.loginView();
    }

    /**
     * Přechod na pohled detail filmu
     * @param movie
     */
    public void moviePageDetailView(MovieSite movie) {
        movieSiteDetailView.init(movie);
        setCenter(movieSiteDetailView);
    }
    
    /**
     * Přechod na pohled upravení filmové stránky
     * @param movieSite
     */
    public void moviePageEditView(MovieSite movieSite) {
        movieSiteEditView.init(movieSite);
        setCenter(movieSiteEditView);
    }

    /**
     * Přechod na pohled detail uživatele
     * @param user
     */
    public void userDetailView(User user) {
        userDetailView.init(user);
        setCenter(userDetailView);
    }
    
    /**
     * Přechod na pohled seznam uživatelů
     * @param event
     */
    public void onUserListViewClick(ActionEvent event) {
        userListView.setUsersCache(null);
        userListView.init();
        setCenter(userListView);
    }

    /**
     * Přechod na pohled vyhledání uživatele
     */
    public void searchUsers() {
        userListView.init();
        setCenter(userListView);
    }
    
    /**
     * Metoda na pohled prohledání diskuzních vláken
     */
    public void searchThreads() {
        forumView.init();
        setCenter(forumView);
    }
    
    /**
     * Přechod na pohled detailu vlákna
     * @param thread
     */
    public void threadView(DiscussionThread thread) {
        threadView.init(thread);
        setCenter(threadView);
    }

    /**
     * Přechod na pohled pro nové diskuzní vlákno
     */
    public void newThreadView() {
        setCenter(threadAddView);
    }

    public UserListView getUserListView() {
        return userListView;
    }

    public void setUserListView(UserListView userListView) {
        this.userListView = userListView;
    }

    public UserDetailView getUserDetailView() {
        return userDetailView;
    }

    public void setUserDetailView(UserDetailView userDetailView) {
        this.userDetailView = userDetailView;
    }

    public ProfileSettingView getProfileView() {
        return profileView;
    }

    public void setProfileView(ProfileSettingView profileView) {
        this.profileView = profileView;
    }

    public ThreadAddView getThreadAddView() {
        return threadAddView;
    }

    public void setThreadAddView(ThreadAddView threadAddView) {
        this.threadAddView = threadAddView;
    }

    public MovieSiteListView getMovieListView() {
        return movieListView;
    }

    public void setMovieListView(MovieSiteListView movieListView) {
        this.movieListView = movieListView;
    }

    public MovieSiteAddView getAddMovieSiteView() {
        return addMovieSiteView;
    }

    public void setAddMovieSiteView(MovieSiteAddView addMovieSiteView) {
        this.addMovieSiteView = addMovieSiteView;
    }

    public MovieSiteDetailView getMovieSiteDetailView() {
        return movieSiteDetailView;
    }

    public void setMovieSiteDetailView(MovieSiteDetailView movieSiteDetailView) {
        this.movieSiteDetailView = movieSiteDetailView;
    }

    public MovieSiteEditView getMovieSiteEditView() {
        return movieSiteEditView;
    }

    public void setMovieSiteEditView(MovieSiteEditView movieSiteEditView) {
        this.movieSiteEditView = movieSiteEditView;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    public ForumView getForumView() {
        return forumView;
    }

    public void setForumView(ForumView forumView) {
        this.forumView = forumView;
    }
    
    
    
    
    

}

    
    
