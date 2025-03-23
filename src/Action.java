import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Action extends Thread implements Serializable {
    //Actions of bomberman
    private String doAction;
    private int bombsCount = 0;
    private GameHandler gameHandler;
    private int x;
    private int y;
    Bomberman bomberman;
    Timer ActionTimer;

    public Action(GameHandler gameHandler, Bomberman bomberman) {
        this.gameHandler = gameHandler;
        this.bomberman = bomberman;
        x = bomberman.getLocation().getX();
        y = bomberman.getLocation().getY();
        ActionTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (doAction == "up" || doAction == "down" || doAction == "right" || doAction == "left") {
                    run();
                }
                doAction = "";
            }
        });
    }

    @Override
    public void run() {
//to save the game
        if (doAction == "save") {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile().getPath());
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(gameHandler.getGameFrame());
                    oos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
//to throw a timerBomb
        if (doAction == "bomb") {
            if(gameHandler.getGameMemory().gameObjects[x][y] instanceof Grass) {
                gameHandler.getGameMemory().gameObjects[x][y] = new Bomb(x, y, gameHandler.getGameMemory().getGameObjects(), gameHandler, this);
                bombsCount++;
                if (!getGameHandler().getGameMemory().getPowerUpParameters().isControlBomb()) {
                    ((Bomb) gameHandler.getGameMemory().gameObjects[x][y]).getExplosion().timerExplosion();
                }
            }
        }
//to throw a controledBomb
        if (doAction == "bombControled") {
            for (int i = 0; i < getGameHandler().getGameMemory().getGameObjectsWidth(); i++) {
                for (int j = 0; j < getGameHandler().getGameMemory().getGameObjectsHeight(); j++) {
                    if (gameHandler.getGameMemory().gameObjects[i][j] instanceof Bomb) {
                        ((Bomb) gameHandler.getGameMemory().gameObjects[i][j]).getExplosion().controledExplosion();
                    }
                }
            }
        }
//to move(4directions)
        if (doAction == "up") {
            checkAndGo(-1, 0);
        }
        if (doAction == "down") {
            checkAndGo(+1, 0);
        }
        if (doAction == "right") {
            checkAndGo(0, +1);
        }
        if (doAction == "left") {
            checkAndGo(0, -1);
        }
        bomberman.getLocation().setX(x);
        bomberman.getLocation().setY(y);
        gameHandler.repaint();
    }

    //checks around cells if bomber man can go
    private void checkAndGo(int dx, int dy) {
        if (gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof BehindWallObjects) {
            if (((BehindWallObjects) (gameHandler.getGameMemory().gameObjects[x + dx][y + dy])).isBroken()) {
                if (gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Door && !(gameHandler.getGameMemory().canGoNextLevel())) {
                    System.out.println("Kill All The Enemies");
                } else {
                    go(dx, dy);
                }
            }else if(gameHandler.getGameMemory().getPowerUpParameters().isGhostMode()){
                go(dx, dy);
            }
        } else if (gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Grass||(gameHandler.getGameMemory().getPowerUpParameters().isGhostMode()&&(gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Block||gameHandler.getGameMemory().gameObjects[x + dx][y + dy] instanceof Wall))) {
            go(dx, dy);
        }
    }

    //after checking this method runs
    private void go(int dx, int dy) {
        gameHandler.getGameMemory().gameMe[x][y] = null;
        x = x + dx;
        y = y + dy;
        gameHandler.getGameMemory().gameMe[x][y] = bomberman;

        try {
            sleep(1000 / gameHandler.getGameMemory().getPowerUpParameters().getBombermanSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getDoAction() {
        return doAction;
    }

    public void setDoAction(String doAction) {
        this.doAction = doAction;
    }

    public int getBombsCount() {
        return bombsCount;
    }

    public void setBombsCount(int bombsCount) {
        this.bombsCount = bombsCount;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
}
