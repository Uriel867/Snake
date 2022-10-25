import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Snake {
    // length
    private int length = 3;
    // linked list that represents snake's body
    ArrayList<BoardPoint> snake = new ArrayList<>();
    // stack that represents it's moving history
    private Stack<MoveDirection> movements = new Stack<>();

    // move function
    public void move(MoveDirection direction) {
        int headRow = snake.get(0).getRow(); //Current row position of the snakes head
        int headCol = snake.get(0).getCol();//Current column position of the snakes head
        BoardPoint lastHeadPos = new BoardPoint(headRow,headCol);

        for (int i = 0; i < snake.size(); i++) {
            //move in the selected direction
            if (snake.get(i).equals(lastHeadPos)) {
                if (direction == MoveDirection.UP)
                    snake.get(i).addToRow();//add later
                else if (direction == MoveDirection.DOWN)
                    snake.get(i).addToRow();//add later
                else if(direction == MoveDirection.LEFT)
                    snake.get(i).addToCol();//add later
                else if(direction == MoveDirection.RIGHT)
                    snake.get(i).addToCol();//add later
            }
        }
    }
}
