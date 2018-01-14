package sample;

import Logic.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.net.URL;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Eilon on 1/13/2018.
 */
public class GameController implements Initializable{
//    @FXML
//    private Button endGame;
//    @FXML
//    private Button startNewGame;
//    @FXML
//    private VBox box;
    @FXML
    private HBox root;

    //Members
    private Game game;
    private BoardFX boardFX;
    private boolean isGameEnded;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game g = this.createGameFromSettings();
        BoardFX gameBoard = new BoardFX(g, 400, 400);
        root.getChildren().add(0, gameBoard);
        gameBoard.setPadding(new Insets(10,10, 10, 10));
        gameBoard.setOnMouseClicked(event -> {
                Pair<Double, Double> loc = new Pair<>(event.getX(),event.getY());
                this.playOneTurn(loc);
        });
    }
    public void playOneTurn(Pair<Double, Double> loc) {
        //Get i,j from loc
        Player p = game.getCurPlayer();
        List<Pair<Integer, Integer>> posMoves = this.game.possibleMoves(p);

    }
    public Game createGameFromSettings() {
        GameSettings gs = new GameSettings();
        try {
            gs.readFromSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Board b = new Board(gs.getSize());
        Rules r = new RegularRules(b);
        Player p1 = new HumanPlayer('X', r);
        Player p2 = new HumanPlayer('O', r);
        p1.setColor(gs.getP1Color());
        p2.setColor(gs.getP2Color());
        return new Game(b,p1,p2,r);
    }

}
