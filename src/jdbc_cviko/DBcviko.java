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

    private TableColumn<Osoba, Integer> radekID;
    private TableColumn<Osoba, String> radekJmeno;
    private TableColumn<Osoba, String> radekPrijmeni;
    private TableColumn<Osoba, String> radekAdresa;

    private ObservableList<Osoba> lidi;
    private TableView<Osoba> table;

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://127.0.0.1:3306";
    private static final String dbUser = "root";
    private static final String dbPassword = "passw0rd";

    private MenuLista menuLista;

    private TextField addID;
    private TextField addJmeno;
    private TextField addPrijmeni;
    private TextField addAdresa;
    
    private String changeItem = "";
    private int changeID = 0;
    
    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        BorderPane border = new BorderPane();
        root.getChildren().add(border);

        menuLista = new MenuLista();

        border.setTop(menuLista.getManBar());
        border.setCenter(createTable());
        border.setBottom(addPanel());

        databazovaFunkce("GET", "SELECT * FROM jdbc_db.osoba");

        Scene scene = new Scene(root, 600, 450);

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

        Button pridatTlacitko = new Button("Pridat");

        pridatTlacitko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                lidi.add(new Osoba(0,Integer.valueOf(addID.getText()), addJmeno.getText(), addPrijmeni.getText(), addAdresa.getText()));

                databazovaFunkce("INSERT", "INSERT INTO jdbc_db.osoba" + " (id_osoba, jmeno, prijmeni, adresa) " + " VALUES (?,?,?,?)");

                addID.clear();
                addJmeno.clear();
                addPrijmeni.clear();
                addAdresa.clear();
            }
        });

        Button odebratTlacitko = new Button("Odebrat");

        odebratTlacitko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        dolniPanel.getChildren().addAll(addID, addJmeno, addPrijmeni, addAdresa, pridatTlacitko, odebratTlacitko);

        return dolniPanel;
    }

    private TableView<Osoba> createTable() {
        table = new TableView<Osoba>();
        lidi = FXCollections.observableArrayList();

        table.setEditable(true);

        radekID = new TableColumn("ID");
        radekID.setEditable(false);
        radekJmeno = new TableColumn("Jmeno");
        radekJmeno.setPrefWidth(110);
        radekPrijmeni = new TableColumn("Prijmeni");
        radekPrijmeni.setPrefWidth(130);
        radekAdresa = new TableColumn("Adresa");
        radekAdresa.setPrefWidth(130);

        radekID.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("osoba_id"));
        radekJmeno.setCellValueFactory(new PropertyValueFactory<Osoba, String>("jmeno"));
        radekPrijmeni.setCellValueFactory(new PropertyValueFactory<Osoba, String>("prijmeni"));
        radekAdresa.setCellValueFactory(new PropertyValueFactory<Osoba, String>("adresa"));

        radekID.setCellFactory(TextFieldTableCell.<Osoba, Integer>forTableColumn(new IntegerStringConverter()));
        radekJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        radekPrijmeni.setCellFactory(TextFieldTableCell.forTableColumn());
        radekAdresa.setCellFactory(TextFieldTableCell.forTableColumn());

        radekID.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, Integer>>() {
            @Override
            public void handle(CellEditEvent<Osoba, Integer> t) {

            }
        }
        );

        radekJmeno.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
                Osoba menenaOsoba = (Osoba) t.getTableView().getItems().get(t.getTablePosition().getRow());
                menenaOsoba.setJmeno(t.getNewValue());
                changeID = menenaOsoba.getId();
                changeItem = menenaOsoba.getJmeno();
//                databazovaFunkce("UPDATE", "UPDATE jdbc_db.osoba SET jmeno = '" + menenaOsoba.getJmeno() + "' WHERE osoba = " + menenaOsoba.getId());
                databazovaFunkce("UPDATE", "UPDATE jdbc_db.osoba SET jmeno = ? WHERE id = ?");
            }
        }
        );

        radekPrijmeni.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
            }
        }
        );

        radekAdresa.setOnEditCommit(new EventHandler<CellEditEvent<Osoba, String>>() {
            @Override
            public void handle(CellEditEvent<Osoba, String> t) {
            }
        }
        );

        table.setItems(lidi);
        table.getColumns().addAll(radekID, radekJmeno, radekPrijmeni, radekAdresa);
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
                
                statement.executeUpdate();
                
            }
            
            if (funkce.equals("UPDATE")) {

                statement = connection.prepareStatement(parametr);
                
                statement.setString(1, changeItem);
                statement.setString(2, Integer.toString(changeID));
                
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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("DB");
                    alert.setHeaderText("Nelze se pripojit k databazi");

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

                lidi.add(new Osoba(id,osoba_id, jmeno, prijmeni, adresa));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcviko.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
