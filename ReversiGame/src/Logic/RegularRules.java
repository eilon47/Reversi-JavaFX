package Logic;

import javafx.util.Pair;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eilon on 1/10/2018.
 */
public class RegularRules implements Rules {
    private Board b;
    public RegularRules(Board b) {
        this.b = b;
    }
    public List<Pair<Integer, Integer>> getPossibleMoves(Player current){
        List<Pair<Integer, Integer>> list = new LinkedList<Pair<Integer, Integer>>();
        char sign = current.getSign();
        int size = b.getBoardSize();
        for(int i =0 ; i< size; i++) {
            for (int j =0; j < size; j++) {
                if(this.b.getBoard()[i][j].getSign() != ' '){
                    continue;
                }
                if(this.isPossibleCell(i, j, sign)){
                    list.add(new Pair<Integer, Integer>(i,j));
                }
            }
        }
        return list;
    }

    public boolean isPossibleCell(int i, int j , char sign){
        //Move for each direction and check if it is;
        for(int rowJ = -1; rowJ < 2; rowJ++) {
            for (int colJ = -1; colJ < 2 ; colJ++) {
                if(rowJ== 0 && colJ ==0) {
                    continue;
                }
                if(this.isPossibleInDirection(i,j, rowJ, colJ, sign)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isPossibleInDirection(int s1,int s2, int jR, int jC, char sign){
        //Step once in one direction.
        int locX = s1 +jR;
        int locY = s2 +jC;
        int size = this.b.getBoardSize();
        if(locX >= size || locY >= size || locX < 0 || locY < 0 ){
            return false;
        }
        char curSign = this.b.getBoard()[locX][locY].getSign();
        //if the first step is on enemy's sign
        while(curSign != sign && curSign != ' '){
            locX += jR;
            locY += jC;
            if(locX >= size || locY >= size || locX < 0 || locY < 0 ){
                return false;
            }
            curSign = this.b.getBoard()[locX][locY].getSign();
            //If you find empty cell after enemy's sign.
            if(curSign == sign){
                return true;
            }
        }
        return false;
    }

    public int flipOnBoard(int s1, int s2, int jR, int jC, char sign){
        //Step once in one direction.
        int locX = s1 +jR;
        int locY = s2 +jC;
        int flipped = 0;
        char c;
        int size = this.b.getBoardSize();
        if(locX >= size || locY >= size || locX < 0 || locY < 0){
            return 0;
        }
        //if the first step is on enemy's sign
        while(this.b.getBoard()[locX][locY].getSign() != sign){
            c = this.b.getBoard()[locX][locY].getSign();
            if(c == ' ') { return 0;}
            if(c == sign) { break; }
            flipped++;
            locX += jR;
            locY += jC;
            if(locX >= size || locY >= size || locX < 0 || locY < 0){
                return 0;
            }
        }
        locX = s1;
        locY = s2;
        for (int i = 0; i <= flipped; i++) {
            this.b.getBoard()[locX][locY].setSign(sign);
            locX += jR;
            locY += jC;
        }
        return flipped;
    }
}
