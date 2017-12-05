/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_cviko;

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

/**
 *
 * @author zenisjan
 */
public class DBcviko extends Application {

    private TableColumn<Osoba, Integer> sloupecID;
    private TableColumn<Osoba, String> sloupecJmeno;
    private TableColumn<Osoba, String> sloupecPrijmeni;
    private TableColumn<Osoba, String> sloupecAdresa;
    private TableColumn<Osoba, String> sloupecPohlavi;
    private TableColumn<Osoba, String> sloupecVzdelani;

    private ObservableList<Osoba> dataOsoby;
    private TableView<Osoba> table;

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://127.0.0.1:3306";
    private static final String dbUser = "root";
    private static final String dbPassword = "passw0rd";


    private TextField addID;
    private TextField addJmeno;
    private TextField addPrijmeni;
    private TextField addAdresa;
    private TextField addPohlavi;
    private TextField addVzdelani;
    
    private String changeItem = "";
    private String changeID = "";
    
    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        BorderPane border = new BorderPane();
        root.getChildren().add(border);

        border.setCenter(createTable());
        border.setBottom(addPanel());

        databazovaFunkce("GET", "SELECT * FROM jdbc_db.osoba");

        Scene scene = new Scene(root, 800, 450);

        primaryStage.setTitle("Java DB");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private FlowPane addPanel() {

        FlowPane dolniPanel = new FlowPane();

        addID = new TextField();
        addID.setPromptText("ID");

        addJmeno = new TextField();
        addJmeno.setPromptText("Jmeno");

        addPrijmeni = new TextField();
        addPrijmeni.setPromptText("Prijmeni");

        addAdresa = new TextField();
        addAdresa.setPromptText("Adresa");
        
        addPohlavi = new TextField();
        addPohlavi.setPromptText("Pohlavi");
        
        addVzdelani = new TextField();
        addVzdelani.setPromptText("Vzdelani");

        Button pridatTlacitko = new Button("Pridat");

        pridatTlacitko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dataOsoby.add(new Osoba(0,Integer.valueOf(addID.getText()), addJmeno.getText(), addPrijmeni.getText(), addAdresa.getText(), addPohlavi.getText(), addVzdelani.getText()));

                databazovaFunkce("INSERT", "INSERT INTO jdbc_db.osoba (id_osoba, jmeno, prijmeni, adresa, pohlavi, vzdelani) VALUES (?,?,?,?,?,?)");

                addID.clear();
                addJmeno.clear();
                addPrijmeni.clear();
                addAdresa.clear();
                addPohlavi.clear();
                addVzdelani.clear();
            }
        });

        Button odebratTlacitko = new Button("Odebrat");

        odebratTlacitko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        dolniPanel.getChildren().addAll(addID, addJmeno, addPrijmeni, addAdresa,addPohlavi,addVzdelani, pridatTlacitko, odebratTlacitko);

        return dolniPanel;
    }

    private TableView<Osoba> createTable() {
        table = new TableView<Osoba>();
        dataOsoby = FXCollections.observableArrayList();

        table.setEditable(true);

        sloupecID = new TableColumn("ID");
        sloupecID.setEditable(false);
        sloupecJmeno = new TableColumn("Jmeno");
        sloupecJmeno.setPrefWidth(110);
        sloupecPrijmeni = new TableColumn("Prijmeni");
        sloupecPrijmeni.setPrefWidth(130);
        sloupecAdresa = new TableColumn("Adresa");
        sloupecAdresa.setPrefWidth(130);
        
        sloupecPohlavi = new TableColumn("Pohlavi");
        sloupecPohlavi.setPrefWidth(130);
        
        sloupecVzdelani = new TableColumn("Vzdelani");
        sloupecVzdelani.setPrefWidth(130);

        sloupecID.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("osoba_id"));
        sloupecJmeno.setCellValueFactory(new PropertyValueFactory<Osoba, String>("jmeno"));
        sloupecPrijmeni.setCellValueFactory(new PropertyValueFactory<Osoba, String>("prijmeni"));
        sloupecAdresa.setCellValueFactory(new PropertyValueFactory<Osoba, String>("adresa"));
        sloupecPohlavi.setCellValueFactory(new PropertyValueFactory<Osoba, String>("pohlavi"));
        sloupecVzdelani.setCellValueFactory(new PropertyValueFactory<Osoba, String>("vzdelani"));

        sloupecID.setCellFactory(TextFieldTableCell.<Osoba, Integer>forTableColumn(new IntegerStringConverter()));
        sloupecJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecPrijmeni.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecAdresa.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecPohlavi.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecVzdelani.setCellFactory(TextFieldTableCell.forTableColumn());

        sloupecVzdelani.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
            Osoba menenaOsoba = (Osoba) t.getTableView().getItems().get(t.getTablePosition().getRow());
            menenaOsoba.setVzdelani(t.getNewValue());
            changeID = Integer.toString(menenaOsoba.getId());
            changeItem = menenaOsoba.getVzdelani();
            databazovaFunkce("UPDATE", "UPDATE jdbc_db.osoba SET vzdelani = ? WHERE id = ?");    
            
            }
        });
        
        sloupecPohlavi.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
            Osoba menenaOsoba = (Osoba) t.getTableView().getItems().get(t.getTablePosition().getRow());
            menenaOsoba.setPohlavi(t.getNewValue());
            changeID = Integer.toString(menenaOsoba.getId());
            changeItem = menenaOsoba.getPohlavi();
            databazovaFunkce("UPDATE", "UPDATE jdbc_db.osoba SET pohlavi = ? WHERE id = ?");
            }
        });
        
        sloupecID.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, Integer>>() {
            @Override
            public void handle(CellEditEvent<Osoba, Integer> t) {

            }
        }
        );

        sloupecJmeno.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
                Osoba menenaOsoba = (Osoba) t.getTableView().getItems().get(t.getTablePosition().getRow());
                menenaOsoba.setJmeno(t.getNewValue());
                changeID = Integer.toString(menenaOsoba.getId());
                changeItem = menenaOsoba.getJmeno();
                databazovaFunkce("UPDATE", "UPDATE jdbc_db.osoba SET jmeno = ? WHERE id = ?");
            }
        }
        );

        sloupecPrijmeni.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
            }
        }
        );

        sloupecAdresa.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
            }
        }
        );

        table.setItems(dataOsoby);
        table.getColumns().addAll(sloupecID, sloupecJmeno, sloupecPrijmeni, sloupecAdresa, sloupecPohlavi, sloupecVzdelani);
        return table;
    }

    public void databazovaFunkce(String funkce, String parametr) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

//          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);

//          provedeni dotazu
            ResultSet rs = null;
            
            if (funkce.equals("GET")) {

                statement = connection.prepareStatement(parametr);

                rs = statement.executeQuery();

                nactiZDB(rs);
                rs.close();
            }

            if (funkce.equals("INSERT")) {

                statement = connection.prepareStatement(parametr);
                
                statement.setString(1, addID.getText());
                statement.setString(2, addJmeno.getText());
                statement.setString(3, addPrijmeni.getText());
                statement.setString(4, addAdresa.getText());
                statement.setString(5, addPohlavi.getText());
                statement.setString(6, addVzdelani.getText());
                
                statement.executeUpdate();
                
            }
            
            if (funkce.equals("UPDATE")) {

                statement = connection.prepareStatement(parametr);
                
                statement.setString(1, changeItem);
                statement.setString(2, changeID);
                
                statement.executeUpdate();
            }

//              uzavreni spojeni
            statement.close();
            connection.close();

        } catch (SQLException | NullPointerException | ClassNotFoundException ex) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ex.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DB");
                    alert.setHeaderText("Chyba pripojeni");

                    alert.showAndWait();
                    System.out.println(ex.getMessage());
                }
            });

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }

        }

    }

    public void nactiZDB(ResultSet rs) {
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int osoba_id = rs.getInt("id_osoba");
                String jmeno = rs.getString("jmeno");
                String prijmeni = rs.getString("prijmeni");
                String adresa = rs.getString("adresa");
                String pohlavi = rs.getString("pohlavi");
                String vzdelani = rs.getString("vzdelani");

                dataOsoby.add(new Osoba(id,osoba_id, jmeno, prijmeni, adresa, pohlavi, vzdelani));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcviko.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
