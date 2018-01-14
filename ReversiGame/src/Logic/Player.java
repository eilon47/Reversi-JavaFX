package Logic;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;


/**
 * Created by Eilon on 1/10/2018.
 */
public interface Player {
    char getSign();
    Color getColor();
    void setColor(Color color);
    int getNumOfSoldier();
    void addSoldiers(int num);
    Pair<Integer, Integer> getPointFromPlayer(List<Pair<Integer, Integer>> possibleLoc);
}
