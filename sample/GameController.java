package sample;

import Logic.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * GameController Class.
 */
public class GameController implements Initializable{
    @FXML
    private Button endGame;
    @FXML
    private Button newGame;
    @FXML
    private VBox box;
    @FXML
    private HBox root;
    @FXML
    private Label curPlayer;
    @FXML
    private Label score1;
    @FXML
    private Label score2;


    //Members
    private Game game;
    private BoardFX gameBoard;
    private List<Pair<Integer, Integer>> nextPosMoves;
    private boolean noTurn;
    private boolean ended;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ended = false;
        game = this.createGameFromSettings();
        double gameH = root.getPrefHeight() - 10;
        gameBoard = new BoardFX(game, gameH, gameH);
        //gameBoard.getStylesheets().add(getClass().getResource("Board.css").toExternalForm());
        root.getChildren().add(0, gameBoard);
        this.setTextLabels();
        this.nextPosMoves = this.game.possibleMoves(this.game.getCurPlayer());
        gameBoard.setOnMouseClicked(event -> {
            if (!ended) {
                this.playOneTurn();
            }
        });
        //Something to check if the game ended in the normal game.
        //gameBoard.addEventHandler();
        this.noTurn = false;
    }

    /**
     * playOneTurn.
     * in charge of playing a move after a mouse click if possible.
     */
    public void playOneTurn() {
        Pair<Integer, Integer> pressLoc = this.wasClicked();
        if(pressLoc == null) {
            return;
        }
        if(!this.nextPosMoves.contains(pressLoc)) {
            this.showAlert("You can't do that move");
            return;
        }
        if(this.nextPosMoves.contains(pressLoc)){
            this.noTurn = false;
            int score = this.game.playOneTurn(pressLoc);
            this.game.setScoreAfterMove(score);
            this.gameBoard.unlightTiles();
            this.gameBoard.flipOnBoardFX();
            this.game.changeTurn();
        }

        if(this.game.getBoard().isBoardFull()) {
            //Show end game message
           showEndGame();
           this.ended = true;

            return;
        }
        List<Pair<Integer, Integer>> posMoves = this.game.possibleMoves(this.game.getCurPlayer());
        if(posMoves.isEmpty()){
            this.showAlert("You have no move.");
            this.game.changeTurn();
            posMoves = this.game.possibleMoves(this.game.getCurPlayer());
            if(posMoves.isEmpty()) {
                this.showAlert("No more moves to do on board, game ended.");
                //End game
                showEndGame();
                this.ended = true;
            }
        }
        this.nextPosMoves = this.game.possibleMoves(this.game.getCurPlayer());
        this.gameBoard.lightTiles();
        this.setTextLabels();  
    }

    /**
     * createGameFromSettings.
     * @return the game initialized with the setting that user picked.
     */
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

    /**
     * wasClicked.
     * @return a tile that was clicked on board.
     */
    public Pair<Integer, Integer> wasClicked() {
        Tile[][] tiles = this.gameBoard.getTiles();
        for(int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].isClicked()){
                    return new Pair<Integer, Integer>(i,j);
                }
            }
        }
        return null;
    }

    /**
     * endGameAction.
     * in charge of switching the scene back to main menu when game is over.
     */
    public void endGameAction(){
        try {
            Stage primaryStage = (Stage) this.endGame.getScene().getWindow();
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
     * newGameAction.
     * in charge of starting a new game.
     */
    public void newGameAction(){
        Stage stage = (Stage) this.newGame.getScene().getWindow();
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

    /**
     * setTextLabels.
     * sets the score of the players.
     */
    public void setTextLabels(){
        score1.setText(Integer.toString(this.game.getP1().getNumOfSoldier()));
        score2.setText(Integer.toString(this.game.getP2().getNumOfSoldier()));
        if(this.game.getTurn()){
            this.curPlayer.setText("Player 1");
        } else {
            this.curPlayer.setText("Player 2");
        }
    }

    /**
     * showAlart.
     * @param message - message to be shown.
     */
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * showEndGame.
     * in charge of alerting the players that the game has ended.
     */
    void showEndGame() {
        String winner;
        int p1Score = game.getP1().getNumOfSoldier();
        int p2Score = game.getP2().getNumOfSoldier();
        if (p1Score > p2Score) {
            winner = "Player 1";
        } else {
            winner = "Player 2";
        }
        String score = "Game Ended!\n the winner is:  " + winner;
        this.showAlert(score);
    }
}
