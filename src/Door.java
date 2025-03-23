import javax.swing.*;
import java.io.Serializable;

//an object to reach you to next level when you've killed all enemies
public class Door extends BehindWallObjects implements Serializable {
    ImageIcon doorIcon = new ImageIcon("door.png");

    public Door() {
    }

    public ImageIcon getImageIcon() {
        if (super.isBroken()){
            return doorIcon;
        }
        return super.getWallIcon();
    }

    public boolean isBroken() {
        return super.isBroken();
    }

    public void setBroken(boolean broken) {
        super.setBroken(broken);
    }

    @Override
    public void doAction(GameHandler gameHandler){
        if(gameHandler.getGameMemory().canGoNextLevel()){
            gameHandler.getGameMemory().stopGame();
            gameHandler.getGameFrame().setVisible(false);
            GameFrame gameFrame=new GameFrame(gameHandler.getGameFrame().LEVEL+1, gameHandler.getGameFrame().width, gameHandler.getGameFrame().height,gameHandler.getGameMemory().score,gameHandler.getGameMemory().getPowerUpParameters());
            gameFrame.setVisible(true);
        }
    }
}
