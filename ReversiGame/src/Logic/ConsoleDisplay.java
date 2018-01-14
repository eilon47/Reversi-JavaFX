package Logic;

/**
 * Created by Eilon on 1/13/2018.
 */
public class ConsoleDisplay implements Display {
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
