
import java.util.ArrayList;
import java.util.Random;


public class Board {
    private ArrayList<Apple> apples;
    private Snake snake;
    private final int ROWS = 100;
    private final int COLUMNS = 100;


    public ArrayList<Apple> spawnApples() {
        ArrayList<Apple> appleList = new ArrayList<>();
        Random rand = new Random();
        //if there are already apples on the board add as much as you need until you hit the max number of apples
        int addThisMuch = MAX_APPLES_NUMBER - apples.size();
        for (int i = 0; i < addThisMuch; i++) {
            int appleRow = rand.nextInt(ROWS);
            int appleCol = rand.nextInt(COLUMNS);
            appleList.add(new Apple(appleRow, appleCol));
        }
        return appleList;
    }

    public void eatApples() {
        //a list to pick up apples
        ArrayList<Apple> eatenApples = new ArrayList<>();
        //if the snakes head has the same position as the apples position eat it and spawn another apple
        for (Apple apple : apples) {
            if (snake.getHeadPos().equals(apple.getPosition())) {
                eatenApples.add(apple);
                spawnApples();
            }
        }
    }
}




