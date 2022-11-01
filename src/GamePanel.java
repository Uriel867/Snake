import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int TILE_SIZE = 25;//48 x 48 tiles

    final int SCREEN_WIDTH = 1300;
    final int SCREEN_HEIGHT = 750;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    ArrayList<Integer> x;
    ArrayList<Integer> y;
    Random rand;
    int FPS = 30;
    int appleX;
    int appleY;

    int applesEaten;
    boolean running = false;

    public GamePanel() {
        rand = new Random();
        //an array list for the snakes body
        x = new ArrayList<>();
        y = new ArrayList<>();
        //snakes head and initial position
        x.add(0);
        y.add(0);
        //second part of the snakes body
        x.add(TILE_SIZE);
        y.add(TILE_SIZE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        addApples();
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
                move();
                eatApples();
                checkCollisions();
                //Draw on screen with updated information
                repaint();
                delta--;


            }

        }
    }


    public void move() {
        //each part of the snakes body gets the position of the following part
        if (x.size() > 1 && y.size() > 1)
            for (int i = x.size() - 1; i > 0; i--) {
                x.set(i, x.get(i - 1));
                y.set(i, y.get(i - 1));

            }
        //move in a specified direction
        if (keyH.direction == 'U') {
            y.set(0, y.get(0) - TILE_SIZE);
        } else if (keyH.direction == 'D') {
            y.set(0, y.get(0) + TILE_SIZE);
        } else if (keyH.direction == 'R') {
            x.set(0, x.get(0) + TILE_SIZE);
        } else if (keyH.direction == 'L') {
            x.set(0, x.get(0) - TILE_SIZE);
        }
    }

    public void paintComponent(Graphics g) {
        if (running) {
            super.paintComponent(g);


            Graphics2D g2 = (Graphics2D) g;
            //draw apple
            g2.setColor(Color.red);
            g2.fillOval(appleX, appleY, TILE_SIZE, TILE_SIZE);
            //draw score
            g2.setColor(Color.CYAN);
            g2.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g2.drawString(" Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
            //draw snake
            for (int i = 0; i < x.size() && i < y.size(); i++) {
                if (i == 0) {
                    g2.setColor(Color.GREEN);
                    g2.fillOval(x.get(i), y.get(i), TILE_SIZE, TILE_SIZE);
                } else {

                    g2.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                    g2.fillRect(x.get(i), y.get(i), TILE_SIZE, TILE_SIZE);

                }
            }

        } else gameOver(g);

    }

    public void addApples() {
        appleX = rand.nextInt((int) (SCREEN_WIDTH / TILE_SIZE)) * TILE_SIZE;
        appleY = rand.nextInt((int) (SCREEN_HEIGHT / TILE_SIZE)) * TILE_SIZE;
    }

    public void eatApples() {
        if (x.get(0) == appleX && y.get(0) == appleY) {
            int xLastElement = x.get(x.size() - 1);
            int yLastElement = y.get(y.size() - 1);
            x.add(xLastElement);
            y.add((yLastElement));
            applesEaten++;
            addApples();
        }
    }

    public void checkCollisions() {
        for (int i = x.size() - 1; i > 0; i--) {
            if (Objects.equals(x.get(0), x.get(i)) && Objects.equals(y.get(0), y.get(i)) && x.size() > 2)
                running = false;
        }
        if (x.get(0) < 0) // left border
            x.set(0, SCREEN_WIDTH);
        if (x.get(0) > SCREEN_WIDTH)// right border
            x.set(0, 0);
        if (y.get(0) < 0)// top border
            y.set(0, SCREEN_HEIGHT);
        if (y.get(0) > SCREEN_HEIGHT)// bottom border
            y.set(0, 0);
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
        g.drawString(SystemName +" You are a loser", (SCREEN_WIDTH - metrics2.stringWidth(SystemName +" You are a loser")) / 2, SCREEN_HEIGHT / 2);
    }
}
