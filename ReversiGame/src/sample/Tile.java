package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

/**
 * Created by Eilon on 1/13/2018.
 */
public class Tile extends StackPane{
    private Circle circle;
    private Pair<Integer, Integer> loc;
    private boolean canBeClicked = false;
    private boolean clicked;
    private boolean turn;
    private Rectangle rec;
    public Tile(Pair<Integer, Integer> loc, double height, double width, char sign) {
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.loc = loc;
        this.canBeClicked = false;
        this.turn = false;
        rec = new Rectangle(width, height);
        rec.setFill(null);
        rec.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        double smaller = Math.min(height, width);
        this.getChildren().add(rec);
        double radius = smaller/2;
        if(sign == 'X') {
            this.circle = new Circle(radius, Color.BLACK);
        } else if (sign == 'O') {
            this.circle = new Circle(radius, Color.WHITE);
        } else {
            this.circle = null;
        }
        setOnMouseClicked(event -> {
            this.circle = new Circle(radius, Color.BLACK);
            this.getChildren().add(circle);
            if(!canBeClicked) {
                return;
            }
            this.clicked = true;
            if(turn) {
                this.circle = new Circle(radius, Color.BLACK);
                this.getChildren().add(circle);
            }
            if(!turn) {
                this.circle = new Circle(radius, Color.WHITE);
                this.getChildren().add(circle);
            }
        });
    }
    public void changeCircleColor() {
        if(this.circle.getFill() == Color.BLACK) {
            this.circle = new Circle(this.circle.getRadius(), Color.WHITE);
        } else if(this.circle.getFill() == Color.WHITE) {
            this.circle = new Circle(this.circle.getRadius(), Color.BLACK);
        }
    }
    public void setCanBeClicked() {
        this.canBeClicked = true;
    }
    public void setCanNotBeClicked() {
        this.canBeClicked = false;
    }
    public Pair<Integer, Integer> getLoc(){
        return this.loc;
    }
    public void notifyTile(boolean turn) {
        this.turn = turn;
    }
    public boolean isClicked() {
        if(clicked){
            this.clicked = false;
            return true;
        }
        return false;
    }
}
