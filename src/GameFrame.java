

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;

public class GameFrame extends JFrame implements KeyListener, Serializable {

    int width;
    int height;
    GameHandler gameHandler;
    InfoPanel infoPanel;
    Start start;
    int LEVEL;
    GameTime gameTime;

    public GameFrame(int LEVEL,int width,int height,int score,PowerUpParameters powerUpParameters) {
        this.width=width;
        this.height=height;
        this.LEVEL=LEVEL;
        setLayout(new BorderLayout());
        setLocation(new Point(100, 100));
        this.setSize(new Dimension(37 * height, 37 * width+30));
        this.setMaximumSize(new Dimension(37 * height, 37 * width+30));
        this.setMinimumSize(new Dimension(37 * height, 37 * width+30));
        gameTime=new GameTime(this);
        gameHandler = new GameHandler(width, height,this,score,powerUpParameters);
        infoPanel=new InfoPanel(this);
        this.add(gameHandler,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);
        start = new Start(width, height, gameHandler,LEVEL);
        addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
//    S:save the game
//    ↑:move up
//    ↓:move down
//    →:move right
//    ←:move left
//    ENTER:leave bomb
//    SPACE:explosion by control
    @Override
    public void keyPressed(KeyEvent e) {
        if(gameHandler.getGameMemory().getBomberman().isAlive()&& !gameHandler.getGameMemory().isGameOverByTime()) {
            if (e.getKeyCode() == KeyEvent.VK_S) {
                gameHandler.getGameMemory().getBomberman().getAction().setDoAction("save");
                gameHandler.getGameMemory().getBomberman().getAction().run();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (gameHandler.getGameMemory().getBomberman().getAction().getBombsCount() < gameHandler.getGameMemory().getPowerUpParameters().getPossibleBombCount()) {
                    gameHandler.getGameMemory().getBomberman().getAction().setDoAction("bomb");
                }
                gameHandler.getGameMemory().getBomberman().getAction().run();
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if(gameHandler.getGameMemory().getPowerUpParameters().isControlBomb()) {
                    gameHandler.getGameMemory().getBomberman().getAction().setDoAction("bombControled");
                    gameHandler.getGameMemory().getBomberman().getAction().run();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gameHandler.getGameMemory().getBomberman().getAction().setDoAction("up");
                gameHandler.getGameMemory().getBomberman().setDirection("up");

            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameHandler.getGameMemory().getBomberman().getAction().setDoAction("down");
                gameHandler.getGameMemory().getBomberman().setDirection("down");
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gameHandler.getGameMemory().getBomberman().getAction().setDoAction("right");
                gameHandler.getGameMemory().getBomberman().setDirection("right");
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gameHandler.getGameMemory().getBomberman().getAction().setDoAction("left");
                gameHandler.getGameMemory().getBomberman().setDirection("left");
            }
            gameHandler.getGameMemory().getBomberman().getAction().ActionTimer.start();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

}
