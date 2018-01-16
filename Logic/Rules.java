package Logic;
import javafx.util.Pair;
import java.util.List;

/**
 * Rules Class.
 */
public interface Rules {
    /**
     * getPossibleMoves.
     * @param current - player to be checked.
     * @return a list of possible moves for player.
     */
    List<Pair<Integer, Integer>> getPossibleMoves(Player current);
    /**
     * isPossibleCell.
     * @param i - row index.
     * @param j - column index.
     * @param sign - sign to start on.
     * @return true if the move is possible and false otherwise.
     */
    boolean isPossibleCell(int i, int j , char sign);
    /**
     * flipOnBoard.
     * @param s1 - start row index.
     * @param s2 start column index.
     * @param jR jump on rows.
     * @param jC - jump on coulumns.
     * @param sign - sign to be checked.
     * @return number of discs the were flipped.
     */
    int flipOnBoard(int s1, int s2, int jR, int jC, char sign);
}
