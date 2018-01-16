package Logic;

/**
 * Board Class
 */

public class Board  {
    private Cell[][] cells;
    private int freeCells;
    private int size;

    /**
     * constructor.
     * @param size - size of the board.
     */
    public Board(int size) {
        this.size = size;
        this.freeCells = size * size;
        this.setBoard();
    }

    /**
     * setBoard.
     * sets the board start move.
     */
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

    /**
     * isBoardFull.
     * @return true if no more free cells and false otherwise.
     */
    public boolean isBoardFull() {
        return (this.freeCells == 0);
    }

    /**
     * addToBoard.
     * @param x - row index.
     * @param y - column index.
     * @param sign - sign to add to board.
     */
    public void addToBoard(int x, int y, char sign) {
        this.cells[x][y].setSign(sign);
        this.freeCells--;
    }

    /**
     * getBoard.
     * @return the cells of the board.
     */
    public Cell[][] getBoard() {
        return this.cells;
    }

    /**
     * getBoardSize.
     * @return the board row/column size.
     */
    public int getBoardSize() {
        return this.size;
    }

    /**
     * toString.
     * @return the board as a string.
     */
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

}