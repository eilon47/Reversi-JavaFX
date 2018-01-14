package Logic;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.Console;
import java.io.SyncFailedException;
import java.util.List;
import java.util.Scanner;

import static sun.misc.Version.println;

/**
 * Created by Eilon on 1/10/2018.
 */
public class HumanPlayer implements Player {
    private char sign;
    private Rules rules;
    private Color color;
    private int numOfSoldiers;
    public HumanPlayer(char sign, Rules r){
        this.sign = sign;
        this.rules = r;
        this.numOfSoldiers = 2;
    }
    public void setColor(Color color) { this.color = color; }
    public Color getColor() { return this.color; }
    public char getSign() {
        return this.sign;
    }
    public int getNumOfSoldier() {
        return this.numOfSoldiers;
    }
    public void addSoldiers(int num) {
        this.numOfSoldiers += num;
    }
    public Pair<Integer, Integer> getPointFromPlayer(List<Pair<Integer, Integer>> possibleLoc) {
        System.out.println("Your possible moves:");
        int size = possibleLoc.size();
        for(int i = 0; i < size; i++) {
            int x = possibleLoc.get(i).getKey();
            int y = possibleLoc.get(i).getValue();
            System.out.print("(" + x + "," + y + ")");
            if(i+1 < size){
                System.out.print(",");
            } else {
                System.out.println();
            }


        }
        int xLoc, yLoc;
        Pair<Integer, Integer> pair;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            try {
                xLoc = scanner.nextInt();
                yLoc = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choose!");
                continue;
            }
            pair = new Pair<Integer, Integer>(xLoc, yLoc);
            if (this.rules.isPossibleCell(xLoc, yLoc, this.sign)) {
                break;
            }
            System.out.println("Invalid choose!");
        }
        return pair;
    }
}
