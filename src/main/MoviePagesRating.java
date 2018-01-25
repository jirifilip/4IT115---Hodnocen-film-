/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.MainController;
import db.Database;
import entity.DiscussionComment;
import entity.DiscussionForum;
import entity.DiscussionThread;
import entity.MovieSite;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import view.login.LoginView;

public class MoviePagesRating extends Application {

    private static final String DB_DRIVER = "com.mysql.jdbc.ClientDriver";
    private static final String dbConnection = "jdbc:derby://127.0.0.1:1527/movies";
    private static final String dbUser = "root";
    private static final String dbPassword = "";


    @Override
    public void start(Stage primaryStage) {
        
        MainController mainController = new MainController(primaryStage);

        mainController.loginView();
        
        Database db = Database.getInstance();
        

    }
    
    public static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            MainController.alert("Chyba", "", "Toto není správně zadaná webová adresa");
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

     

}
