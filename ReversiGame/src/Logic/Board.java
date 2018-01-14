package Logic;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by Eilon on 1/10/2018.
 */
public class Board extends GridPane {
    private Cell[][] cells;
    private int freeCells;
    private int size;



    public Board(int size) {
        this.size = size;
        this.freeCells = size * size;
        this.setBoard();
    }

    private void setBoard() {
        this.cells = new Cell[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.cells[i][j] = new Cell();
                this.cells[i][j].setSign(' ');
            }
        }
        int mid = this.size / 2 - 1;
        this.cells[mid][mid].setSign('O');
        this.cells[mid + 1][mid + 1].setSign('O');
        this.cells[mid + 1][mid].setSign('X');
        this.cells[mid][mid + 1].setSign('X');
        this.freeCells -= 4;
    }

    public boolean isBoardEmpty() {
        return (this.freeCells == 0);
    }

    public void addToBoard(int x, int y, char sign) {
        this.cells[x][y].setSign(sign);
        this.freeCells--;
    }

    public Cell[][] getBoard() {
        return this.cells;
    }

    public int getBoardSize() {
        return this.size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------\n");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                char c = this.cells[i][j].getSign();
                sb.append("| " + c + " ");
            }
            sb.append("|\n");
            sb.append("---------------------------------\n");
        }
        return sb.toString();
    }

    public void draw() {
        this.getChildren().clear();
        //if (startGame) {
            int height = (int) this.getPrefHeight();
            int width = (int) this.getPrefWidth();

            double cellHeight = height / cells.length;
            double cellWidth = width / cells[0].length;

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j].getSign() == ' ') {
                        this.add(new Rectangle(cellWidth, cellHeight,
                                Color.YELLOWGREEN), j, i);
                    } else {
                        this.add(new Rectangle(cellWidth, cellHeight,
                                Color.YELLOWGREEN), j, i);
                        if (cells[i][j].getSign() == 'X') {
                            this.add(new Rectangle(cellWidth - 2, cellHeight - 2,
                                    Color.BLACK), j, i);
                        } else {
                            this.add(new Rectangle(cellWidth - 2, cellHeight - 2,
                                    Color.WHITE), j, i);
                        }
                    }
                }
            }
    }


}