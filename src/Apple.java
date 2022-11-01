import java.util.Random;

public class Apple {
    int appleX;
    int appleY;
    int applesEaten;
    int tileSize;
    Random rand;

    public Apple(){
        rand = new Random();
        appleX = rand.nextInt(GamePanel.SCREEN_WIDTH);
        appleY = rand.nextInt(GamePanel.SCREEN_HEIGHT);
        applesEaten = 0;
        tileSize = GamePanel.TILE_SIZE;
    }

    public void addApples() {
        appleX = rand.nextInt((int) (GamePanel.SCREEN_WIDTH / tileSize)) * tileSize;
        appleY = rand.nextInt((int) (GamePanel.SCREEN_HEIGHT / tileSize)) * tileSize;
    }

    public void eatApples() {
        int xCoordinate = SnakeBody.xCoordinate(0);
        int yCoordinate = SnakeBody.yCoordinate(0);
        if (xCoordinate == appleX && yCoordinate == appleY) {
            int xLastElement = SnakeBody.xCoordinate(SnakeBody.snakeSize() - 1);
            int yLastElement = SnakeBody.yCoordinate(SnakeBody.snakeSize() - 1);
            SnakeBody.increaseSnake();
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
