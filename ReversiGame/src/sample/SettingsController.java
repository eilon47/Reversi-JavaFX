package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Eilon on 1/13/2018.
 */
public class SettingsController implements Initializable {
    @FXML
    private ColorPicker p1Color;
    @FXML
    private ColorPicker p2Color;
    @FXML
    private ChoiceBox<Integer> size;
    @FXML
    private Button apply;
    @FXML
    private Button cancel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    public void apply(){
        try {
            GameSettings gs = new GameSettings();
            gs.writeTOSettings(this.size.getValue(), this.p1Color.getValue(), this.p2Color.getValue());
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Stage primaryStage = (Stage) this.apply.getScene().getWindow();
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,520,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void cancel(){
        try {
            Stage primaryStage = (Stage) this.cancel.getScene().getWindow();
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,520,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
