import javax.swing.*;

public class Menu {
    JMenu options;
    JMenuItem restartMenuItem, exitMenuItem,pauseMenuItem;
    SnakeBody snake;

    public Menu(JFrame frame, SnakeBody snake) {
        this.snake = snake;
        JMenuBar menuBar = new JMenuBar();
        options = new JMenu("Options");
        restartMenuItem = new JMenuItem("Restart");
        exitMenuItem = new JMenuItem("Exit");
        pauseMenuItem = new JMenuItem("Pause");

        restartMenuItem.addActionListener(e -> {
            GamePanel.running = true;
            snake.clearSnake();
        });
        pauseMenuItem.addActionListener(e -> {
            GamePanel.paused = true;
        });
        exitMenuItem.addActionListener(e -> {
            GamePanel.running = false;
            GamePanel.exitGameReason = ExitGameReason.PLAYER_PRESSED_EXIT;
        });

        options.add(restartMenuItem);
        options.add(pauseMenuItem);
        options.add(exitMenuItem);
        menuBar.add(options);
        menuBar.setVisible(true);
        options.setVisible(true);
        restartMenuItem.setVisible(true);
        pauseMenuItem.setVisible(true);
        exitMenuItem.setVisible(true);

        frame.setJMenuBar(menuBar);
    }
}