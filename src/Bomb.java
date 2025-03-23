import javax.swing.*;
import java.io.Serializable;

public class Bomb extends GameObject implements Serializable {
    ImageIcon bombIcon = new ImageIcon("bomb.jpg");
    private Explosion explosion;

    public Bomb(int x, int y, GameObject[][] gameObjects, GameHandler gameHandler, Action action) {
        super.setLocation(new Location(x,y));
        explosion=new Explosion(this,gameHandler,action);
    }

    public ImageIcon getImageIcon() {
        return bombIcon;
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    public Explosion getExplosion() {
        return explosion;
    }

}
