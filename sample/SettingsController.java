package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * SettingsController class.
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

    /**
     * apply.
     * apply all the changes in setting that were made.
     */
    public void apply(){
        try {
            GameSettings gs = new GameSettings();
            Color color1 = this.p1Color.getValue();
            Color color2 = this.p2Color.getValue();
            if(color1.toString().equals(color2.toString())) {
                this.showAlert("Please choose different colors");
                return;
            }
            gs.writeTOSettings(this.size.getValue(), this.p1Color.getValue(), this.p2Color.getValue());
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Stage primaryStage = (Stage) this.apply.getScene().getWindow();
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,600,400);
            scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ignores all changes that been done and returns to main menu.
     */
    public void cancel(){
        try {
            Stage primaryStage = (Stage) this.cancel.getScene().getWindow();
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,600,400);
            scene.getStylesheets().add(getClass().getResource("MenuStyle").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * showAlart.
     * @param message - message to be witten in alert.
     */
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
