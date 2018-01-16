package Logic;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.util.List;


/**
 * Player Class
 */
public interface Player {
    /**
     * getSign.
     * @return the plaer's sign.
     */
    char getSign();

    /**
     * getColor.
     * @return the player's color.
     */
    Color getColor();

    /**
     * setColor.
     * @param color color to be set.
     */
    void setColor(Color color);

    /**
     * getNumOfSoldiers.
     * @return the player's number of soldiers on board.
     */
    int getNumOfSoldier();

    /**
     * addSoldiers.
     * @param num number of soldiers to be added.
     */
    void addSoldiers(int num);
    /**
     * getPointFromPlayer.
     * @param possibleLoc - the list of player's possible moves.
     * @return - the selected move.
     */
    Pair<Integer, Integer> getPointFromPlayer(List<Pair<Integer, Integer>> possibleLoc);
}
