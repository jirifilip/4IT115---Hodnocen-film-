/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import controller.MainController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Jirka_
 */
public class RegisterView extends GridPane {

    private final double width = 500;
    private final double height = 500;
    
    private Stage primaryStage;
    private Scene scene;
    
    // komponenty
    private Text labelText = new Text("Registrace");
    
    private Label loginLabel = new Label("Registrace");
    
    private Label usernameLabel = new Label("Uživatelské jméno");
    private Label emailLabel = new Label("Email");
    private Label passwordLabel = new Label("Heslo");
    
    
    private TextField usernameTextField = new TextField();
    private TextField emailTextField = new TextField();
    private PasswordField passwordTextField = new PasswordField();
    
    
    private Button registerButton = new Button("Registrovat se");
    private Button backToLoginButton = new Button("Zpět");
    
    
    private MainController controller;
    
    
    public RegisterView(MainController controller) {
        this.controller = controller;
        
        scene = new Scene(this, width, height);
        this.primaryStage = controller.getPrimaryStage();
        
        init();
    }

    private void init() {
        
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        labelText.setFont(Font.font ("Verdana", 30));
        labelText.setFill(Color.GRAY);
        
        add(labelText, 0, 0, 2, 1);
        
        add(usernameLabel, 0, 1);
        add(usernameTextField, 1, 1);
        
        add(emailLabel, 0, 2);
        add(emailTextField, 1, 2);
        
        add(passwordLabel, 0, 3);
        add(passwordTextField, 1, 3);
        
        add(registerButton, 1, 4);
        add(backToLoginButton, 1, 5);
        


        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
        registerButton.setOnAction(this::onRegisterButtonClick);
        backToLoginButton.setOnAction(e -> {
            controller.loginView();
        });

    }
    
    
    private void onRegisterButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        
        if (!(username.isEmpty() || email.isEmpty() || password.isEmpty())) {
            User user = new User(username, email, password);
        }
        
        controller.mainView();
    }
    
}
