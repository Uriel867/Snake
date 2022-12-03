import java.util.ArrayList;
import java.util.Objects;

public class SnakeBody {
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    GamePanel gp;

    KeyHandler moveToDirection = new KeyHandler();

    //a constructor to set initial position of the snake
    public SnakeBody(int xPos, int yPos, GamePanel gp) {
        this.gp = gp;
        x = new ArrayList<>();
        y = new ArrayList<>();
        x.add(xPos);
        y.add(yPos);


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
            y.set(0, y.get(0) - gp.TILE_SIZE);
        } else if (moveToDirection == 'D') {
            y.set(0, y.get(0) + gp.TILE_SIZE);
        } else if (moveToDirection == 'R') {
            x.set(0, x.get(0) + gp.TILE_SIZE);
        } else if (moveToDirection == 'L') {
            x.set(0, x.get(0) - gp.TILE_SIZE);
        }


    }

    public void checkCollisions() {
        for (int i = x.size() - 1; i > 0; i--) {
            if (Objects.equals(x.get(0), x.get(i)) && Objects.equals(y.get(0), y.get(i)) && x.size() > 2)
                GamePanel.running = false;
        }
        if (x.get(0) < 0) // left border
            x.set(0, gp.SCREEN_WIDTH);
        if (x.get(0) > gp.SCREEN_WIDTH)// right border
            x.set(0, 0);
        if (y.get(0) < 0)// top border
            y.set(0, gp.SCREEN_HEIGHT);
        if (y.get(0) > gp.SCREEN_HEIGHT)// bottom border
            y.set(0, 0);
    }

    public  int snakeSize() {
        return x.size();
    }

    public  int xCoordinate(int i) {
        return x.get(i);
    }

    public  int yCoordinate(int i) {
        return y.get(i);
    }

    public  void increaseSnake() {
        int addToX = x.get(x.size() - 1);
        int addToY = y.get(y.size() - 1);
        x.add(addToX);
        y.add(addToY);
    }
}
