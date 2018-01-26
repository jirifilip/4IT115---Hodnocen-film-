/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entity.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * UI třída pro pohled login na aplikaci.
 * 
 * 
 * @author Jirka_
 */
public class LoginView extends GridPane {

    private final double width = 500;
    private final double height = 500;
    
    private Stage primaryStage;
    private Scene scene;
    
    private MainController controller;
    
    // komponenty
    private Text applicationLogo = new Text("Stream Tracker");
    
    private Label loginLabel = new Label("Přihlašovací jméno");
    private TextField usernameTextField = new TextField();
    
    private Label passwordLabel = new Label("Heslo");
    private PasswordField passwordTextField = new PasswordField();
    
    private Button loginButton = new Button("Přihlásit se");
    private Button registerButton = new Button("Registrovat se");
    private Button withoutLoginButton = new Button("Pokračovat bez přihlášení");
    
    /**
     * Konstruktor login.
     * @param controller
     */
    public LoginView(MainController controller) {
        scene = new Scene(this, width, height);
        scene.getStylesheets().add("style/main.css");
        
        this.controller = controller;
        
        this.primaryStage = this.controller.getPrimaryStage();
        
        
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        setStyle("-fx-background: black;");
        
        init();
    }

    /**
     * Inicializace pohledu
     */
    public void init() {

        applicationLogo.setFont(Font.font ("Verdana", 40));
        applicationLogo.setFill(Color.GRAY);
        
        add(applicationLogo, 0, 0, 2, 1);
        
        add(loginLabel, 0, 1);
        add(usernameTextField, 1, 1);
        
        add(passwordLabel, 0, 2);
        add(passwordTextField, 1, 2);
        
        add(loginButton, 1, 3);
        add(registerButton, 1, 4);
        add(withoutLoginButton, 1, 5);
       
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
        loginButton.setOnAction(this::onLoginButtonClick);
        registerButton.setOnAction(e -> controller.registerView());
        
        withoutLoginButton.setOnAction(e -> {
            controller.mainView();
            controller.alert("Informace", "Vstup do aplikace",
                    "Pro plnou funkčnost aplikace se prosím registrujte nebo přihlašte!");
        });

    }
    
    /**
     * Handler pro kliknutí na tlačítko loginbutton
     */
    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        User user = User.fetchUser(username, password);

        if (user != null) {
            controller.setCurrentUser(user);
            controller.mainView();
        } else {
            controller.alert("Chyba", "Špatná kombinace jména a hesla", "");
        }
        
        
    }
    
    
    
}
