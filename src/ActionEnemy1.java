import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

public class ActionEnemy1 extends ActionEnemy implements Serializable {

    private GameHandler gameHandler;
    private Random random = new Random();
    private int randMove;
    private Enemy1 enemy1;


    public ActionEnemy1(final GameHandler paintPanel, final Enemy1 enemy1) {
        this.gameHandler = paintPanel;
        this.enemy1 = enemy1;
        super.moveTimer = new Timer(1000, new ActionListener() {
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
        while (enemy1.isAlive()) {
            randMove = random.nextInt(4);
            if (randMove % 4 == 0 ) {
                go(-1,0,"up");
            }
            if (randMove % 4 == 1) {
                go(+1,0,"down");
            }
            if (randMove % 4 == 2) {
                go(0,+1,"right");
            }
            if (randMove % 4 == 3) {
                go(0,-1,"left");
            }
            gameHandler.repaint();
        }
    }
    private void go(int dx,int dy,String direction){
        if (!(gameHandler.getGameMemory().gameBeings[enemy1.getX()+dx][enemy1.getY() +dy] instanceof Enemy)) {
            if (gameHandler.getGameMemory().gameObjects[enemy1.getX()+dx][enemy1.getY() +dy] instanceof Grass || gameHandler.getGameMemory().gameObjects[enemy1.getX()+dx][enemy1.getY() +dy] instanceof Fire) {
                gameHandler.getGameMemory().gameBeings[enemy1.getX()][enemy1.getY()] = null;
                enemy1.setX(enemy1.getX() +dx);
                enemy1.setY(enemy1.getY() +dy);
                enemy1.setDirection(direction);
                gameHandler.getGameMemory().gameBeings[enemy1.getX()][enemy1.getY()] = enemy1;
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
