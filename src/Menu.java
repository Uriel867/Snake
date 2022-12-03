import javax.swing.*;

public class Menu {
    JMenu options;
    JMenuItem restartMenuItem, exitMenuItem;
    SnakeBody snake;

    public Menu(JFrame frame, SnakeBody snake) {
        this.snake = snake;
        JMenuBar menuBar = new JMenuBar();
        options = new JMenu("Options");
        restartMenuItem = new JMenuItem("Restart");
        exitMenuItem = new JMenuItem("Exit");

        restartMenuItem.addActionListener(e -> {
            GamePanel.running = true;
            snake.clearSnake();
        });
        exitMenuItem.addActionListener(e -> {
            GamePanel.running = false;
            GamePanel.exitGameReason = ExitGameReason.PLAYER_PRESSED_EXIT;
        });

        options.add(restartMenuItem);
        options.add(exitMenuItem);
        menuBar.add(options);
        menuBar.setVisible(true);
        options.setVisible(true);
        restartMenuItem.setVisible(true);
        exitMenuItem.setVisible(true);

        frame.setJMenuBar(menuBar);
    }
}