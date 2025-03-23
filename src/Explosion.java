import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Explosion extends Thread implements Serializable {

    private Bomb bomb;
    private GameHandler gameHandler;
    private Action action;
    private int x;
    private int y;
    Timer explosionTimer;


    public Explosion(Bomb bomb, final GameHandler gameHandler, Action action) {
        this.bomb = bomb;
        this.x = bomb.getLocation().getX();
        this.y = bomb.getLocation().getY();
        this.gameHandler = gameHandler;
        this.action = action;
//        timer for bombs( 5 sec)
        explosionTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }

    //to explode other bombs around this bomb without timer
    public void explodeNow() { start(); }

    //to explode this bomb by timer(in usual way)
    public void timerExplosion() {
        explosionTimer.start();
    }

    //to explode bombs by control(by VK_SPACE key)
    public void controledExplosion() {
        start();
    }


    //  fire around bomb will be ineffective after 0.5 sec and can't burn beings
    public void StopBurning() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < gameHandler.getGameMemory().getGameObjectsWidth(); i++) {
            for (int j = 0; j < gameHandler.getGameMemory().getGameObjectsHeight(); j++) {
                if (gameHandler.getGameMemory().gameFire[i][j] instanceof Fire) {
                    if(!(gameHandler.getGameMemory().gameObjects[i][j] instanceof BehindWallObjects)) {
                        gameHandler.getGameMemory().gameObjects[i][j] = new Grass();
                    }else {
                        if(!((BehindWallObjects)(gameHandler.getGameMemory().gameObjects[i][j])).isBroken()) {
                            ((BehindWallObjects) (gameHandler.getGameMemory().gameObjects[i][j])).setBroken(true);
                        }else{
                            gameHandler.getGameMemory().gameObjects[i][j] = new Grass();
                        }
                    }
                    gameHandler.getGameMemory().gameFire[i][j] = null;
                }
            }
        }
        gameHandler.repaint();
    }


    @Override
    public void run() {
        this.explosionTimer.stop();
        explode();
        gameHandler.repaint();
        StopBurning();
        action.setBombsCount(action.getBombsCount() - 1);
    }

    //to check destroy and burn beings and walls around cells in 4directions
    public void explode() {
        gameHandler.getGameMemory().gameFire[x][y] = new Fire("center");
        gameHandler.getGameMemory().gameObjects[x][y] = new Grass();
        for (int i = 1; i <= gameHandler.getGameMemory().getPowerUpParameters().getBombRadius(); i++) {
            if (gameHandler.getGameMemory().gameFire[x - i + 1][y] instanceof Fire && !(gameHandler.getGameMemory().gameObjects[x - i + 1][y] instanceof Wall)) {
                checkAndBurn(-i, 0, "up");
            } else {
                break;
            }
        }
        for (int i = 1; i <= gameHandler.getGameMemory().getPowerUpParameters().getBombRadius(); i++) {
            if (gameHandler.getGameMemory().gameFire[x + i - 1][y] instanceof Fire && !(gameHandler.getGameMemory().gameObjects[x + i - 1][y] instanceof Wall)) {
                checkAndBurn(+i, 0, "down");
            } else {
                break;
            }
        }
        for (int i = 1; i <= gameHandler.getGameMemory().getPowerUpParameters().getBombRadius(); i++) {
            if (gameHandler.getGameMemory().gameFire[x][y - i + 1] instanceof Fire && !(gameHandler.getGameMemory().gameObjects[x][y - i + 1] instanceof Wall)) {
                checkAndBurn(0, -i, "left");
            } else {
                break;
            }
        }
        for (int i = 1; i <= gameHandler.getGameMemory().getPowerUpParameters().getBombRadius(); i++) {
            if (gameHandler.getGameMemory().gameFire[x][y + i - 1] instanceof Fire && !(gameHandler.getGameMemory().gameObjects[x][y + i - 1] instanceof Wall)) {
                checkAndBurn(0, +i, "right");
            } else {
                break;
            }
        }

    }

    //check each given cells if its possible to destroy and burn
    private void checkAndBurn(int dx, int dy, String direction) {
        if ((gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Wall || gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Grass) && !(gameHandler.getGameMemory().gameFire[x + dx][y + dy] instanceof Fire)) {
            if (gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Wall) {
                gameHandler.getGameMemory().score = gameHandler.getGameMemory().score + 10;
            }
            gameHandler.getGameMemory().gameFire[x + dx][y + dy] = new Fire(direction);
        }
        if (gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Bomb && !gameHandler.getGameMemory().getPowerUpParameters().isControlBomb()) {
            ((Bomb) (gameHandler.getGameMemory().gameObjects[x + dx][y + dy])).getExplosion().explodeNow();
        }
        if (gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof BehindWallObjects) {
            if (!((BehindWallObjects) (gameHandler.getGameMemory().gameObjects[x + dx][y + dy])).isBroken()) {
                gameHandler.getGameMemory().gameFire[x + dx][y + dy] = new Fire(direction);
                gameHandler.getGameMemory().score = gameHandler.getGameMemory().score + 10;
            } else if (!(gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Door)) {
                gameHandler.getGameMemory().gameFire[x + dx][y + dy] = new Fire(direction);
            }
        }
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

}
