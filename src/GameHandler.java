import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

//the class for change memory to graphic
public class GameHandler extends JPanel implements Serializable {
    private GameMemory gameMemory;
    private GameFrame gameFrame;
    private JLabel[][] map;
    private ImageIcon bombAndBomberman = new ImageIcon("bomb&bomberman.png");
    private ImageIcon bombermanGhostDown = new ImageIcon("downGhost.jpg");
    private ImageIcon bombermanGhostUp = new ImageIcon("upGhost.jpg");
    private ImageIcon bombermanGhostRight = new ImageIcon("rightGhost.jpg");
    private ImageIcon bombermanGhostLeft = new ImageIcon("leftGhost.jpg");

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < gameMemory.getGameObjectsWidth(); i++) {
            for (int j = 0; j < gameMemory.getGameObjectsHeight(); j++) {
                map[i][j].setIcon(gameMemory.gameObjects[i][j].getImageIcon());
//                bomberman
                if (gameMemory.gameMe[i][j] instanceof Bomberman) {
                    map[i][j].setIcon(gameMemory.gameMe[i][j].getImageIcon());
                    if (gameMemory.gameObjects[i][j] instanceof Bomb) {
                        map[i][j].setIcon(bombAndBomberman);
                    }
                    if (gameMemory.gameObjects[i][j] instanceof Block || gameMemory.gameObjects[i][j] instanceof Wall || (gameMemory.gameObjects[i][j] instanceof BehindWallObjects &&!((BehindWallObjects)(gameMemory.gameObjects[i][j])).isBroken())){
                        if (((Bomberman) (gameMemory.gameMe[i][j])).getDirection() == "down") {
                            map[i][j].setIcon(bombermanGhostDown);
                        }
                        if (((Bomberman) (gameMemory.gameMe[i][j])).getDirection() == "up") {
                            map[i][j].setIcon(bombermanGhostUp);
                        }
                        if (((Bomberman) (gameMemory.gameMe[i][j])).getDirection() == "left") {
                            map[i][j].setIcon(bombermanGhostLeft);
                        }
                        if (((Bomberman) (gameMemory.gameMe[i][j])).getDirection() == "right") {
                            map[i][j].setIcon(bombermanGhostRight);
                        }
                    }
                }
//                enemies
                if (gameMemory.gameBeings[i][j] != null) {
                    map[i][j].setIcon(gameMemory.gameBeings[i][j].getImageIcon());
                }
//                fire and kill or destroy
                if (gameMemory.gameFire[i][j] != null) {
                    map[i][j].setIcon(gameMemory.gameFire[i][j].getImageIcon());
                    if (getGameMemory().gameBeings[i][j] instanceof Enemy) {
                        ((Enemy) (getGameMemory().gameBeings[i][j])).setAlive(false);
                        gameMemory.score = gameMemory.score + ((Enemy) (gameMemory.gameBeings[i][j])).getLevel() * 20;
                        getGameMemory().gameBeings[i][j] = null;
                    }
                    if (getGameMemory().gameMe[i][j] instanceof Bomberman) {
                        ((Bomberman) (getGameMemory().gameMe[i][j])).setAlive(false);
                        getGameMemory().gameMe[i][j] = null;
                        new TryAgain(gameFrame);
                    }
                }
//                death by encountering an enemy with bomberman
                if (gameMemory.gameBeings[i][j] instanceof Enemy && gameMemory.gameMe[i][j] instanceof Bomberman) {
                    ((Enemy) (gameMemory.gameBeings[i][j])).setAlive(false);
                    gameMemory.gameBeings[i][j] = null;
                    ((Bomberman) (getGameMemory().gameMe[i][j])).setAlive(false);
                    gameMemory.gameMe[i][j] = null;
                    gameMemory.gameObjects[i][j] = new Fire("bang");
                    map[i][j].setIcon(gameMemory.gameObjects[i][j].getImageIcon());
                    new TryAgain(gameFrame);
                }
//                getting a poerUp
                if (getGameMemory().gameObjects[i][j] instanceof BehindWallObjects && gameMemory.gameMe[i][j] instanceof Bomberman) {
                    if (((BehindWallObjects) (getGameMemory().gameObjects[i][j])).isBroken()) {
                        ((BehindWallObjects) (getGameMemory().gameObjects[i][j])).doAction(this);
                        getGameMemory().gameObjects[i][j] = new Grass();
                    }
                }
            }
        }
    }


    public GameHandler(int width, int height, GameFrame gameFrame, int score, PowerUpParameters powerUpParameters) {
        this.gameFrame = gameFrame;
        gameMemory = new GameMemory(gameFrame, score, powerUpParameters);
        setLayout(new GridLayout(width, height));
        map = new JLabel[width][height];
        this.setBackground(Color.WHITE);
        for (int i = 0; i < gameMemory.getGameObjectsWidth(); i++) {
            for (int j = 0; j < gameMemory.getGameObjectsHeight(); j++) {
                map[i][j] = new JLabel();
                this.add(map[i][j]);
            }
        }
    }

    public GameMemory getGameMemory() {
        return gameMemory;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

}

