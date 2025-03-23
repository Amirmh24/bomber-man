import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

public class ActionEnemy2 extends ActionEnemy implements Serializable {

    private GameHandler gameHandler;
    private Random random = new Random();
    private int randMove;
    private Enemy2 enemy2;
    private boolean gotCloser = false;

    public ActionEnemy2(final GameHandler paintPanel, final Enemy2 enemy2) {
        this.gameHandler = paintPanel;
        this.enemy2 = enemy2;
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
        while (enemy2.isAlive()) {
            gotCloser = false;
            getClose(-1, 0, "up");
            getClose(+1, 0, "down");
            getClose(0, +1, "right");
            getClose(0, -1, "left");
//            The enemy will move random 10 times,if it doesn't have possible move to get closer
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
        if (Math.abs(((enemy2.getX() + dx) - gameHandler.getGameMemory().getBomberman().getLocation().getX())) < Math.abs(((enemy2.getX()) - gameHandler.getGameMemory().getBomberman().getLocation().getX())) || Math.abs(((enemy2.getY() + dy) - gameHandler.getGameMemory().getBomberman().getLocation().getY())) < Math.abs(((enemy2.getY()) - gameHandler.getGameMemory().getBomberman().getLocation().getY()))) {
            go(dx, dy, direction);
        }
    }

    public void go(int dx, int dy, String direction) {
        if (!(gameHandler.getGameMemory().gameBeings[enemy2.getX() + dx][enemy2.getY() + dy] instanceof Enemy) && enemy2.isAlive()) {
            if (gameHandler.getGameMemory().gameObjects[enemy2.getX() + dx][enemy2.getY() + dy] instanceof Grass || gameHandler.getGameMemory().gameObjects[enemy2.getX() + dx][enemy2.getY() + dy] instanceof Fire) {
                gameHandler.getGameMemory().gameBeings[enemy2.getX()][enemy2.getY()] = null;
                enemy2.setX(enemy2.getX() + dx);
                enemy2.setY(enemy2.getY() + dy);
                enemy2.setDirection(direction);
                gameHandler.getGameMemory().gameBeings[enemy2.getX()][enemy2.getY()] = enemy2;
                gotCloser = true;
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
