import javax.swing.*;
import java.io.Serializable;

//the main game charecter
public class Bomberman extends GameObject implements Serializable {
    private ImageIcon bombermanDownIcon = new ImageIcon("down.jpg");
    private ImageIcon bombermanUpIcon = new ImageIcon("up.jpg");
    private ImageIcon bombermanRightIcon = new ImageIcon("right.jpg");
    private ImageIcon bombermanLeftIcon = new ImageIcon("left.jpg");
    private Action action;
    private boolean isAlive=true;
    private String direction="down";

    public ImageIcon getImageIcon() {
        if(direction=="up"){
            return bombermanUpIcon;
        }
        if(direction=="down"){
            return bombermanDownIcon;
        }
        if(direction=="right"){
            return bombermanRightIcon;
        }
        if(direction=="left"){
            return bombermanLeftIcon;
        }
        return bombermanDownIcon;
    }

    public Bomberman(GameHandler gameHandler) {
        setLocation(new Location(1, 1));
        action= new Action(gameHandler, this);
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        if(alive==false){
            this.action.getGameHandler().getGameMemory().stopGame();
        }
        isAlive = alive;
    }


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
