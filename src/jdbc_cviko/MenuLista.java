/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_cviko;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author zenisjan
 */
public class MenuLista {

    private MenuBar menuBar = new MenuBar();

    public MenuLista() {
        initMenu();
    }

    public MenuBar getManBar() {
        return menuBar;
    }

    private void initMenu() {

        menuBar = new MenuBar();
        menuBar.useSystemMenuBarProperty().set(true);

        Menu menuPolozkaSoubor = new Menu("_Soubor");

        MenuItem konec = new MenuItem("_Konec");

        konec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                System.exit(0);

            }
        });

        menuPolozkaSoubor.getItems().addAll(konec);

        Menu help = new Menu("Pomoc");

        MenuItem oProgramu = new MenuItem("O programu");

        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

         

            }
        });

        MenuItem napoveda = new MenuItem("Napoveda");
        napoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                WebView webView = new WebView();
//                webView.getEngine().load(xyz.class.getResource("/zdroje/napoveda.html").toExternalForm());

                stage.setScene(new Scene(webView, 500, 500));
                stage.show();

            }
        });

        help.getItems().addAll(oProgramu, napoveda);

        menuBar.getMenus().addAll(menuPolozkaSoubor, help);

    }

}
