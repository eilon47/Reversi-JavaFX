package sample;

import Logic.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by green on 11/01/2018.
 */
public class MenuController  {
    @FXML
    private Button start;
    @FXML
    private Button exit;
    @FXML
    private Button settings;



    @FXML
    void startGame() {
        Stage stage = (Stage) start.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        try {
            HBox root = (HBox) loader.load();
            loader.setController(new GameController());
            Scene gameScene = new Scene(root, 600, 400);
            stage.setScene(gameScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void getSettings() {
        Stage stage = (Stage) settings.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        try {
            GridPane root = (GridPane) loader.load();
            loader.setController(new SettingsController());
            Scene settingsScene = new Scene(root, 400, 400);
            stage.setScene(settingsScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitGame() {
        Stage s = (Stage) this.exit.getScene().getWindow();
        s.close();
    }
}
