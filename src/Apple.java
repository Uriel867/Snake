import java.util.Random;

public class Apple {
    int appleX;
    int appleY;
    int applesEaten;
    GamePanel gp;
    SnakeBody snake;
    Random rand;

    public Apple(GamePanel gp,SnakeBody snake){
        this.gp = gp;
        this.snake = snake;
        rand = new Random();
        appleX = rand.nextInt(gp.SCREEN_WIDTH);
        appleY = rand.nextInt(gp.SCREEN_HEIGHT);
        applesEaten = 0;

    }

    public void addApples() {
        appleX = rand.nextInt((int) (gp.SCREEN_WIDTH / gp.TILE_SIZE)) * gp.TILE_SIZE;
        appleY = rand.nextInt((int) (gp.SCREEN_HEIGHT / gp.TILE_SIZE)) * gp.TILE_SIZE;
    }

    public void eatApples() {
        int xCoordinate = snake.xCoordinate(0);
        int yCoordinate = snake.yCoordinate(0);
        if (xCoordinate == appleX && yCoordinate == appleY) {
            int xLastElement = snake.xCoordinate(snake.snakeSize() - 1);
            int yLastElement = snake.yCoordinate(snake.snakeSize() - 1);
            snake.increaseSnake();
            applesEaten++;
            addApples();
        }
    }
    public int getAppleX(){
        return appleX;
    }
    public int getAppleY(){
        return appleY;
    }

    public int getApplesEaten(){
        return applesEaten;
    }
}
