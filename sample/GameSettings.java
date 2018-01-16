package sample;
import java.io.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * GameSettings Class.
 */
public class GameSettings {
    private File settings;
    private Color p1;
    private Color p2;
    private int size;
    private boolean readyForGame;

    /**
     * constructor.
     */
    public GameSettings() {
        this.settings = new File("settings.txt");
        this.readyForGame = false;
    }

    /**
     * isReadyForGame.
     * @return true if ready for game and false otherwise.
     */
    public boolean isReadyForGame(){
        return this.readyForGame;
    }

    /**
     * getP1Color.
     * @return - p1 color.
     */
    public Color getP1Color(){
        return this.p1;
    }
    /**
     * getP2Color.
     * @return - p2 color.
     */
    public Color getP2Color() {
        return this.p2;
    }

    /**
     * getSize.
     * @return - the board size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * writeTOSettings.
     * @param size - size to be written.
     * @param p1Color - p1 color to be set.
     * @param p2Color - p2 color to be set.
     * @throws Exception - if there is a problem with writing to settings.
     */
    public void writeTOSettings(int size, Color p1Color, Color p2Color) throws Exception{
        BufferedWriter writer;
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(settings)));
        String boardSize = Integer.toString(size);
        String color1 = p1Color.toString();
        String color2 = p2Color.toString();
        writer.write(boardSize);
        writer.newLine();
        writer.write(color1);
        writer.newLine();
        writer.write(color2);
        writer.newLine();
        writer.close();

    }

    /**
     * readFromSettings.
     * @throws Exception - if there is a problem with reading the settings.
     */
    public void readFromSettings() throws Exception{
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(settings)));
        String line = reader.readLine();
        ArrayList<String> args = new ArrayList<>();
        while(line != null) {
            args.add(line);
            line = reader.readLine();
        }
        this.size = Integer.parseInt(args.get(0));
        this.p1 = Color.valueOf(args.get(1));
        this.p2 = Color.valueOf(args.get(2));
        this.readyForGame = true;
    }
}
