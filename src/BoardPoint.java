public class BoardPoint {

    private int row;
    private int col;

    public BoardPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void addToRow(int row){
        this.row += row;
    }

    public void addToCol(int col){
        this.col += col;
    }

    public boolean equals(BoardPoint other) {
        return other.row == row && other.col == col;
    }
}
