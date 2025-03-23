import javax.swing.*;
import java.io.Serializable;

//all things like grass,bomb,wall,block,bomberman,enemy,etc
public abstract class GameObject implements Serializable {
    private Location location;
    private ImageIcon imageIcon;

    public Location getLocation() { return location; }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

}
