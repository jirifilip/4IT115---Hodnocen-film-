/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import controller.MainController;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Crypto;

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
    private Label pictureNameLabel = new Label("[...]");

    
    private FileChooser fileChooser = new FileChooser();
    private Button openFileButton = new Button("Zvol profilovou fotku...");
    private File profilePicture;
    
    
    private TextField usernameTextField = new TextField();
    private TextField emailTextField = new TextField();
    private PasswordField passwordTextField = new PasswordField();
    
    
    private Button registerButton = new Button("Registrovat se");
    private Button backToLoginButton = new Button("Zpět");
    
    
    public static final String usernameRegex = "([a-zA-Z0-9]){5,15}";
    public static final String emailRegex = "(.+)@(.+)";
    public static final String passwordRegex = ".{5,15}";
    
    
    private MainController controller;
    
    
    public RegisterView(MainController controller) {
        this.controller = controller;
        
        scene = new Scene(this, width, height);
        scene.getStylesheets().add("style/main.css");
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
        
        add(pictureNameLabel, 0, 4);
        add(openFileButton, 1, 4);
        
        add(registerButton, 1, 5);
        add(backToLoginButton, 1, 6);
        
        
        


        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
        registerButton.setOnAction(this::onRegisterButtonClick);
        backToLoginButton.setOnAction(e -> {
            controller.loginView();
        });
        
        openFileButton.setOnAction(e -> {
            profilePicture = fileChooser.showOpenDialog(primaryStage);
            if (profilePicture != null) {
                pictureNameLabel.setText(profilePicture.getName());
            }
        });

    }
    
    
    private void onRegisterButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        
        String currentDir = System.getProperty("user.dir");
        
        if (username.matches(usernameRegex) &&
            email.matches(emailRegex) && password.matches(passwordRegex)) {
            
            User user = new User(username, email, password);
            controller.setCurrentUser(user);
            user.reload();

            try {
                if (profilePicture != null) {
                    Path sourcePath = profilePicture.toPath();
                    String destPath = currentDir + "/src/resources/" + user.getUsername() + ".jpg";
                    Files.copy(sourcePath, new File(destPath).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    
                    user.setProfilePhotoUrl(destPath);
                    user.update();
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            controller.alert("Oznámení", "Registrace dokončena", 
                        "Vítejte v aplikaci!");
            
            controller.mainView();
        } else {
            controller.alert("Oznámení", "", 
                        "Špatně zadané uživatelské jméno, email nebo heslo.");
        }
        
        
    }
    
}
