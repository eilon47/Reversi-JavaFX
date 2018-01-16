package Logic;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.util.List;
import java.util.Scanner;

/**
 * Human player class.
 */
public class HumanPlayer implements Player {
    private char sign;
    private Rules rules;
    private Color color;
    private int numOfSoldiers;

    /**
     * constructor.
     * @param sign - player's sign.
     * @param r - games rules.
     */
    public HumanPlayer(char sign, Rules r){
        this.sign = sign;
        this.rules = r;
        this.numOfSoldiers = 2;
    }

    /**
     * setColor.
     * @param color - color to be set.
     */
    public void setColor(Color color) { this.color = color; }

    /**
     * getColor.
     * @return the player color.
     */
    public Color getColor() { return this.color; }

    /**
     * getSign.
     * @return the player's sign.
     */
    public char getSign() {
        return this.sign;
    }

    /**
     * getNumOfSoldier.
     * @return the player's number of soldiers.
     */
    public int getNumOfSoldier() {
        return this.numOfSoldiers;
    }

    /**
     * addSoldiers.
     * @param num number of soldiers to be added.
     */
    public void addSoldiers(int num) {
        this.numOfSoldiers += num;
    }

    /**
     * getPointFromPlayer.
     * @param possibleLoc - the list of player's possible moves.
     * @return - the selected move.
     */
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
