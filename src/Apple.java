public class Apple {
    private BoardPoint position;

    public Apple(int row,int col){
        position = new BoardPoint(row,col);
    }

    public BoardPoint getPosition(){
        return position;
    }
}
