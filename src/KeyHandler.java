import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed;

    char direction = 'R';

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                if(direction != 'R') {
                    direction = 'L';
                    GamePanel.paused = false;
                }
                break;
            case KeyEvent.VK_D:
                if(direction != 'L') {
                    direction = 'R';
                    GamePanel.paused = false;
                }
                break;
            case KeyEvent.VK_W:
                if(direction != 'D') {
                    direction = 'U';
                    GamePanel.paused = false;
                }
                break;
            case KeyEvent.VK_S:
                if(direction != 'U') {
                    direction = 'D';
                    GamePanel.paused = false;
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
