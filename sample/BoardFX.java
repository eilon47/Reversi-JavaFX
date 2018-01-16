package sample;

import Logic.Game;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.util.List;

/**
 * BoardFx Class.
 * in charge of drawing the board.
 */
public class BoardFX extends GridPane {
    private Game game;
    private Tile[][] tiles;
    private List<Pair<Integer, Integer>> nextPosMoves;

    /**
     * constructor.
     * @param game - the board's game.
     * @param prefH - the prefHeight of board.
     * @param prefW - the prefWidth of board.
     */
    public BoardFX(Game game, double prefH, double prefW) {
        //this.getStylesheets().add(getClass().getResource("BoardStyle.css").toExternalForm());
        this.setPrefWidth(prefW);
        this.setPrefHeight(prefH);
        this.game = game;
        int size = this.game.getBoard().getBoardSize();
        double heightCell = this.getPrefHeight()/size;
        double widthCell = this.getPrefWidth()/size;
        this.tiles = new Tile[size][size];
        for(int i =0; i < size; i++) {
            this.tiles[i] = new Tile[size];
            for(int j =0; j < size; j++) {
                Pair<Integer, Integer> loc = new Pair<>(i,j);
                char sign = this.game.getBoard().getBoard()[i][j].getSign();
                Color tileColor;
                if(sign == 'O') {
                    tileColor = this.game.getP2().getColor();
                } else if( sign == 'X') {
                    tileColor = this.game.getP1().getColor();
                } else {
                    tileColor = Color.TRANSPARENT;
                }
                this.tiles[i][j] = new Tile(loc, heightCell, widthCell, tileColor);

                this.add(tiles[i][j], i, j);
            }
        }
        this.lightTiles();
    }

    /**
     * getTiles.
     * @return the tiles of the Board.
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }
    public void flipOnBoardFX() {
        int size = this.game.getBoard().getBoardSize();
        for(int i =0 ; i< size; i++) {
            for (int j = 0; j < size; j++) {
                char sign = this.game.getBoard().getBoard()[i][j].getSign();
                if(sign == 'X'){
                    this.tiles[i][j].changeCircleColor(game.getP1().getColor());
                }
                if(sign == 'O') {
                    this.tiles[i][j].changeCircleColor(game.getP2().getColor());
                }
            }
        }
    }

    /**
     * lightTiles.
     * lights the tiles which the player can play on.
     */
    public void lightTiles() {
        this.nextPosMoves = this.game.possibleMoves(this.game.getCurPlayer());
        System.out.println("Player: " + this.game.getCurPlayer().getSign());
        for(Pair<Integer, Integer> p : this.nextPosMoves) {
            System.out.println(p.getKey() +","+ p.getValue());
            int x = p.getKey();
            int y = p.getValue();
            this.tiles[x][y].light(Color.LIGHTYELLOW);
        }
    }

    /**
     * unlightTiles.
     * unlights all the tiles that were lighten.
     */
    public void unlightTiles(){
        for(Pair<Integer, Integer> p : this.nextPosMoves) {
            int x = p.getKey();
            int y = p.getValue();
            this.tiles[x][y].unlight();
        }
    }
}
