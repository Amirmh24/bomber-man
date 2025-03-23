import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

public class ActionEnemy4 extends ActionEnemy implements Serializable {

    private GameHandler gameHandler;
    private Random random = new Random();
    private int randMove;
    private Enemy4 enemy4;
    private boolean gotCloser =false;

    public ActionEnemy4(final GameHandler paintPanel, final Enemy4 enemy4) {
        this.gameHandler = paintPanel;
        this.enemy4 = enemy4;
        super.moveTimer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start();
                } catch (IllegalThreadStateException f) {
                }
            }
        });
    }

    @Override
    public void run() {
        //The enemy moves while it's alive
        while (enemy4.isAlive()) {
            gotCloser = false;
            getClose(-1, 0, "up");
            getClose(+1, 0, "down");
            getClose(0, +1, "right");
            getClose(0, -1, "left");
            //The enemy will move random 10 times,if it doesn't have possible move to get closer
            while (!gotCloser) {
                for (int i = 0; i < 10; i++) {
                    randMove = random.nextInt(4);
                    if (randMove % 4 == 0) {
                        go(-1, 0, "up");
                    }
                    if (randMove % 4 == 1) {
                        go(+1, 0, "down");
                    }
                    if (randMove % 4 == 2) {
                        go(0, +1, "right");
                    }
                    if (randMove % 4 == 3) {
                        go(0, -1, "left");
                    }
                }
            }
            gameHandler.repaint();
        }
    }
    //        getting close by getClose() methode
    private void getClose(int dx, int dy, String direction) {
        if (Math.abs(((enemy4.getX() + dx) - gameHandler.getGameMemory().getBomberman().getLocation().getX())) < Math.abs(((enemy4.getX()) - gameHandler.getGameMemory().getBomberman().getLocation().getX())) || Math.abs(((enemy4.getY() + dy) - gameHandler.getGameMemory().getBomberman().getLocation().getY())) < Math.abs(((enemy4.getY()) - gameHandler.getGameMemory().getBomberman().getLocation().getY()))) {
            go(dx, dy, direction);
        }
    }

    public void go(int dx, int dy, String direction) {
        if (!(gameHandler.getGameMemory().gameBeings[enemy4.getX() + dx][enemy4.getY() + dy] instanceof Enemy) && enemy4.isAlive()) {
            if (gameHandler.getGameMemory().gameObjects[enemy4.getX() + dx][enemy4.getY() + dy] instanceof Grass || gameHandler.getGameMemory().gameObjects[enemy4.getX() + dx][enemy4.getY() + dy] instanceof Fire||gameHandler.getGameMemory().gameObjects[enemy4.getX() + dx][enemy4.getY() + dy] instanceof Block ||gameHandler.getGameMemory().gameObjects[enemy4.getX() + dx][enemy4.getY() + dy] instanceof Wall) {
                gameHandler.getGameMemory().gameBeings[enemy4.getX()][enemy4.getY()] = null;
                enemy4.setX(enemy4.getX() + dx);
                enemy4.setY(enemy4.getY() + dy);
                enemy4.setDirection(direction);
                gameHandler.getGameMemory().gameBeings[enemy4.getX()][enemy4.getY()] = enemy4;
                gotCloser = true;
                try {
                    this.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
