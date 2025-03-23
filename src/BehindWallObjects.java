import javax.swing.*;
import java.io.Serializable;

//the object which is behind of wall before destroying wall(like Door and powerUps)
public class BehindWallObjects extends GameObject implements Serializable {
    private ImageIcon wallIcon = new ImageIcon("wall.png");
    private boolean isBroken=false;

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public ImageIcon getWallIcon() {
        return wallIcon;
    }

    public void doAction(GameHandler gameHandler){
    }
}
