import java.util.ArrayList;
import java.util.Objects;

public class SnakeBody {
    static ArrayList<Integer> x;
    static ArrayList<Integer> y;
    int tileSize;

    KeyHandler moveToDirection = new KeyHandler();

    //a constructor to set initial position of the snake
    public SnakeBody(int xPos,int yPos){
        x = new ArrayList<>();
        y = new ArrayList<>();
        x.add(xPos);
        y.add(yPos);
        tileSize = GamePanel.TILE_SIZE;

    }

    public void move(char moveToDirection) {

        //each part of the snakes body gets the position of the following part
        if (x.size() > 1 && y.size() > 1)
            for (int i = x.size() - 1; i > 0; i--) {
                x.set(i, x.get(i - 1));
                y.set(i, y.get(i - 1));

            }
        //move in a specified direction
        if (moveToDirection == 'U') {
            y.set(0, y.get(0) - tileSize);
        } else if (moveToDirection == 'D') {
            y.set(0, y.get(0) + tileSize);
        } else if (moveToDirection == 'R') {
            x.set(0, x.get(0) + tileSize);
        } else if (moveToDirection == 'L') {
            x.set(0, x.get(0) - tileSize);
        }


        }

    public void checkCollisions() {
        for (int i = x.size() - 1; i > 0; i--) {
            if (Objects.equals(x.get(0), x.get(i)) && Objects.equals(y.get(0), y.get(i)) && x.size() > 2)
                GamePanel.running = false;
        }
        if (x.get(0) < 0) // left border
            x.set(0, GamePanel.SCREEN_WIDTH);
        if (x.get(0) > GamePanel.SCREEN_WIDTH)// right border
            x.set(0, 0);
        if (y.get(0) < 0)// top border
            y.set(0, GamePanel.SCREEN_HEIGHT);
        if (y.get(0) > GamePanel.SCREEN_HEIGHT)// bottom border
            y.set(0, 0);
    }
    public static int snakeSize(){
        return x.size();
    }

    public static int xCoordinate(int i){
        return x.get(i);
    }

    public static int yCoordinate(int i){
        return y.get(i);
    }

    public static void increaseSnake(){
        int addToX = x.get(x.size() - 1);
        int addToY = y.get(y.size() - 1);
        x.add(addToX);
        y.add(addToY);
    }
}
