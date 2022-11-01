import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    static final int TILE_SIZE = 25;//48 x 48 tiles

    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;

    Thread gameThread;
    SnakeBody snake;
    Apple apple;
    Random rand;
    int FPS = 30;

    KeyHandler keyH = new KeyHandler();

    static boolean running = false;

    public GamePanel() {
        rand = new Random();
        snake = new SnakeBody(0, 0);
        apple = new Apple();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        apple.addApples();
        running = true;
        gameThread = new Thread(this);
        //calls run method
        gameThread.start();
    }

    //This is the core of the game, the game loop
    @Override
    public void run() {
//sort of delay between repainting our screen
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;


            lastTime = currentTime;
            if (delta >= 1) {   //update information on the board
                snake.move(keyH.direction);
                apple.eatApples();
                snake.checkCollisions();
                //Draw on screen with updated information
                repaint();
                delta--;


            }

        }
    }


    public void paintComponent(Graphics g) {
        if (running) {
            super.paintComponent(g);
            int snakeSize = snake.snakeSize();

            Graphics2D g2 = (Graphics2D) g;
            //draw apple
            g2.setColor(Color.red);
            g2.fillOval(apple.getAppleX(), apple.getAppleY(), TILE_SIZE, TILE_SIZE);
            //draw score
            g2.setColor(Color.CYAN);
            g2.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g2.drawString(" Score: " + apple.getApplesEaten(), (SCREEN_WIDTH - metrics.stringWidth("Score: " + apple.getApplesEaten())) / 2, g.getFont().getSize());
            //draw snake
            for (int i = 0; i < snakeSize; i++) {
                int xCoordinate = snake.xCoordinate(i);
                int yCoordinate = snake.yCoordinate(i);
                if (i == 0) {
                    g2.setColor(Color.GREEN);
                    g2.fillOval(xCoordinate, yCoordinate, TILE_SIZE, TILE_SIZE);
                } else {

                    g2.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                    g2.fillRect(xCoordinate, yCoordinate, TILE_SIZE, TILE_SIZE);

                }
            }

        } else gameOver(g);

    }


    public void gameOver(Graphics g) {
        //Game Over text
        String SystemName = null;
        try {
            // get system name
            SystemName = System.getProperty("user.name");
        } catch (Exception E) {
            System.err.println(E.getMessage());
        }
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString(SystemName + " You are a loser", (SCREEN_WIDTH - metrics2.stringWidth(SystemName + " You are a loser")) / 2, SCREEN_HEIGHT / 2);
    }
}
