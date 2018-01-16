package Logic;

/**
 * Cell Class
 */
public class Cell {
    //members.
    private char sign ;

    /**
     * constructor.
     */
    public Cell(){
        this.sign = ' ';
    }

    /**
     * setSign.
     * @param sign sign to be set in cell.
     */
    public void setSign(char sign) {
        this.sign = sign;
    }

    /**
     * getSign.
     * @return the cell sign.
     */
    public char getSign() { return this.sign;}
}
