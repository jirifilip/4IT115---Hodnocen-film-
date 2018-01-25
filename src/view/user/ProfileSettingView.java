/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import controller.MainController;
import entity.MovieSite;
import entity.User;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Jirka_
 */
public class ProfileSettingView extends GridPane {
    
    private MainController controller;
    
    private Label nameField = new Label();
    private Label emailField = new Label();
    private TextArea descriptionField = new TextArea();
    private PasswordField newPasswordField = new PasswordField();
    private PasswordField confirmPasswordField = new PasswordField();
    
    private CheckBox adminBox = new CheckBox();
    
    private Label nameLabel = new Label("Uživatelské jméno");
    private Label emailLabel = new Label("Email");
    private Label descLabel = new Label("Popis");
    private Label adminLabel = new Label("Admin");
    private Label passwordLabel = new Label("Nové heslo");
    private Label confirmPasswordLabel = new Label("Potvrďte heslo");
    
    
    private Label pictureNameLabel = new Label("[...]");
    
    private FileChooser fileChooser = new FileChooser();
    private Button openFileButton = new Button("Zvol profilovou fotku...");
    private File profilePicture;
    
    
    private Button saveButton = new Button("Uložit nastavení");
    
    public ProfileSettingView(MainController controller) {
        this.controller = controller;
        
        this.setAlignment(Pos.TOP_CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
    }

    public void init(User user) {
        System.out.println("blabla");
        getChildren().clear();
        
        nameField.setText(user.getUsername());
        emailField.setText(user.getEmail());

        descriptionField.setText(user.getProfileDescription());
        descriptionField.setMaxWidth(250);
        
        this.add(nameLabel, 0, 0);
        this.add(nameField, 1, 0);
        
        this.add(emailLabel, 0, 1);
        this.add(emailField, 1, 1);
        
        this.add(descLabel, 0, 2);
        this.add(descriptionField, 1, 2);
        
        this.add(passwordLabel, 0, 3);
        this.add(newPasswordField, 1, 3);
        
        this.add(confirmPasswordLabel, 0, 4);
        this.add(confirmPasswordField, 1, 4);
        
        this.add(pictureNameLabel, 0, 5);
        this.add(openFileButton, 1, 5);
        
        if (controller.getCurrentUser().isAdmin()) {
            adminBox.setSelected(user.isAdmin());
        
            this.add(adminLabel, 0, 6);
            this.add(adminBox, 1, 6);
        }
        
        this.add(saveButton, 1, 7);
        
        openFileButton.setOnAction(e -> {
            profilePicture = fileChooser.showOpenDialog(new Stage());
            if (profilePicture != null) {
                pictureNameLabel.setText(profilePicture.getName());
            }
        });
        
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            user.setUsername(name);
            
            String desc = descriptionField.getText();
            user.setProfileDescription(desc);
            
            String email = emailField.getText();
            user.setEmail(email);
            
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            String currentDir = System.getProperty("user.dir");
            
            try {
                if (profilePicture != null) {
                    Path sourcePath = profilePicture.toPath();
                    String destPath = currentDir + "/src/resources/" + user.getUsername() + ".jpg";
                    Files.copy(sourcePath, new File(destPath).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            
            if (!newPassword.isEmpty()) {
                if (newPassword.equals(confirmPassword)) {
                    user.setPassword(newPassword);
                } else {
                    controller.alert("Chyba", "Hesla se ti nerovnají, šéfe!", "");
                }
            }
            
            if (!controller.getCurrentUser().isAdmin()) {
                if (adminBox.isSelected()) {
                    user.setAdmin(adminBox.isSelected());
                }
            }
            
            user.update();
            
            controller.getMainAppView().onUserListViewClick(e);
       });
    }
    
}
