package sample;

import Logic.Game;
import Logic.Player;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Eilon on 1/14/2018.
 */
public class BoardFX extends GridPane {
    private Game game;
    private Tile[][] tiles;
    public BoardFX(Game game, double prefH, double prefW) {
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
                this.tiles[i][j] = new Tile(loc, heightCell, widthCell, sign);
                this.add(tiles[i][j], j, i);
            }
        }
    }
    public void notifyAllTiles(List<Pair<Integer, Integer>> possibles) {
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                tiles[i][j].notifyTile(this.game.getTurn());
                if(possibles.contains(tiles[i][j].getLoc())){
                    tiles[i][j].setCanBeClicked();
                } else {
                    tiles[i][j].setCanNotBeClicked();
                }
            }
        }
    }
    public void runGame() {
        while(!this.game.isEndGame()) {
            Player cur = this.game.getCurPlayer();
            List<Pair<Integer, Integer>> posMoves = this.game.possibleMoves(cur);
            if(posMoves.isEmpty()) {
                this.game.changeTurn();
                //Message to player
                continue;
            }
            Pair<Integer, Integer> loc = null;
            while(loc == null) {
                loc = this.getClick();
            }
            this.game.playOneTurn(loc);


        }
    }
    public Pair<Integer, Integer> getClick() {
        Pair<Integer, Integer> loc;
        for(int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].isClicked()) {
                    return tiles[i][j].getLoc();
                }
            }
        }
        return null;
    }


}
