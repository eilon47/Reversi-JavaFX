package Logic;

/**
 * ConsoleDisplay Class
 */
public class ConsoleDisplay implements Display {
    /**
     * constructor.
     */
    public ConsoleDisplay(){}

    @Override
    public void print(Object o) {
        System.out.print(o);
    }

    @Override
    public void println(Object o) {
        System.out.println(o);
    }
}
