package Logic;

import javafx.util.Pair;
import sample.BoardFX;


import java.util.List;

/**
 * Created by Eilon on 1/10/2018.
 */
public class Game {
    Board board;
    private Cell[][] cells;
    private Player p1;
    private Player p2;
    private Rules r;
    private boolean turn;
    private boolean endGame;

    public Game(Board b, Player p1, Player p2, Rules r){
        board = b;
        cells = b.getBoard();
        this.p1 = p1;
        this.p2 = p2;
        this.r = r;
        this.turn = true;
        this.endGame = false;
    }

    public Player getCurPlayer(){
        if(turn){
            return this.p1;
        }
        return p2;
    }
    public Player getNextPlayer(){
        if(turn){
            return this.p2;
        }
        return p1;
    }

    public void run(){
        boolean noTurns = false;
        System.out.print(this.board.toString());
        while(!board.isBoardFull()){
            this.printScore();
            Player curPlayer = this.getCurPlayer();
            Player nextPlayer = this.getNextPlayer();
            System.out.println("Current turn: " + curPlayer.getSign());
            List<Pair<Integer, Integer>> posMoves;
            posMoves = this.r.getPossibleMoves(curPlayer);
            if(posMoves.isEmpty()) {
                if(!noTurns) {
                    System.out.println("You don't have any move, turn passes to the other player.");
                    noTurns = true;
                    turn = !turn;
                    continue;
                } else {
                    System.out.println("No moves for both players - game ended.");
                    this.endGame();
                    break;
                }
            }
            noTurns = false;
            Pair<Integer, Integer> point = this.getCurPlayer().getPointFromPlayer(posMoves);
            int score = this.playOneTurn(point);
            this.setScoreAfterMove(score);
            System.out.println(this.board.toString());
            this.turn = !this.turn;
        }
        this.endGame();

    }
    public int playOneTurn(Pair<Integer, Integer> point){
        int row = point.getKey();
        int col = point.getValue();
        int score = 0;
        char playerSign = this.getCurPlayer().getSign();
        this.board.addToBoard(row, col, playerSign);
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j< 2; j++ ){
                if(i == 0 && j == 0) {
                    continue;
                }
                score += this.r.flipOnBoard(row,col, i, j, playerSign);
            }
        }
        return score;
    }
    public void setScoreAfterMove(int num){
        if(turn){
            p1.addSoldiers(num +1);
            p2.addSoldiers(-num);
        } else {
            p2.addSoldiers(num +1);
            p1.addSoldiers(-num);
        }
    }
    public void endGame() {
        this.endGame = true;
        char winner, loser;
        int winScore;
        if (this.p1.getNumOfSoldier() == this.p2.getNumOfSoldier()) {
            System.out.println("Game ended with a tide.");
            return;
        }
        if (this.p1.getNumOfSoldier() > this.p2.getNumOfSoldier()) {
            winner = this.p1.getSign();
            winScore = this.p1.getNumOfSoldier();
            loser = this.p2.getSign();
        } else {
            winner = this.p2.getSign();
            winScore = this.p2.getNumOfSoldier();
            loser = this.p1.getSign();
        }
        System.out.println("Winner is: " + winner + "with a score of: " + winScore+ " points!" );
        System.out.println(loser + "maybe next time");
    }

    public void printScore() {
        char c1 = p1.getSign();
        char c2 = p2.getSign();
        int x1 = p1.getNumOfSoldier();
        int x2 = p2.getNumOfSoldier();
        System.out.println("Scores: " + c1 + ": " + x1 + ", " + c2 + ": " + x2);
    }
    public Board getBoard() { return this.board;}
    public boolean getTurn() { return this.turn;}
    public void changeTurn() { this.turn = !this.turn;}
    public Player getP1() { return this.p1;}
    public Player getP2() { return this.p2;}
    public List<Pair<Integer, Integer>> possibleMoves(Player player) {
        return this.r.getPossibleMoves(player);
    }
    public boolean isEndGame() {
        return this.endGame;
    }

}
