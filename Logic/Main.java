package Logic;


/**
 * Main class of logic.
 */
public class Main {
    public static void main(String args[]){
        Board b= new Board(8);
        Rules r = new RegularRules(b);
        Player p1 = new HumanPlayer('X', r);
        Player p2 = new HumanPlayer('O', r);
        Game g = new Game(b,p1,p2,r);
        g.run();
    }
}
