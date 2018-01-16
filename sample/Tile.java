package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

/**
 * Tile Class.
 */
public class Tile extends StackPane{
    private Circle circle;
    private Pair<Integer, Integer> loc;
    private boolean canBeClicked = false;
    private Color color;
    private boolean clicked;
    private boolean turn;
    private Rectangle rec;

    /**
     * constructor.
     * @param loc - tile location on the board.
     * @param height - height of tile.
     * @param width - width of tile.
     * @param color - color of tile.
     */
    public Tile(Pair<Integer, Integer> loc, double height, double width, Color color) {
        this.color = color;
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.loc = loc;
        this.canBeClicked = false;
        this.turn = false;
        rec = new Rectangle(width, height);
        rec.setStrokeWidth(0.5);
        rec.setFill(null);
        rec.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        double smaller = Math.min(height, width);
        this.getChildren().add(rec);
        double radius = smaller/3;
        this.circle = new Circle(radius, this.color);
        this.getChildren().add(circle);
        setOnMouseClicked(event -> {
            this.clicked = true;
        });
    }

    /**
     * changeCircleColor.
     * @param color - color to change to.
     */
    public void changeCircleColor(Color color) {
        this.getChildren().remove(circle);
        this.color = color;
        this.circle = new Circle(this.circle.getRadius(), this.color);
        this.getChildren().add(circle);
    }

    /**
     * getLoc.
     * @return the location of the tile in the board.
     */
    public Pair<Integer, Integer> getLoc(){
        return this.loc;
    }

    /**
     * isClicked.
     * @return true if was just clicked and false otherwise.
     */
    public boolean isClicked() {
        if(clicked){
            this.clicked = false;
            return true;
        }
        return false;
    }

    /**
     * light.
     * @param color - color to light the tile with.
     */
    public void light(Color color) {
        this.getChildren().remove(rec);
        this.rec.setFill(color);
        this.getChildren().add(rec);
    }

    /**
     * unlight.
     * unlights the tile.
     */
    public void unlight() {
        this.getChildren().remove(rec);
        this.rec.setFill(Color.TRANSPARENT);
        this.getChildren().add(rec);
    }
}
