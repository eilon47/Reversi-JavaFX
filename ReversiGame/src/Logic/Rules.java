package Logic;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Eilon on 1/10/2018.
 */
public interface Rules {
    List<Pair<Integer, Integer>> getPossibleMoves(Player current);
    boolean isPossibleCell(int i, int j , char sign);
    int flipOnBoard(int s1, int s2, int jR, int jC, char sign);
}
