import javax.swing.*;
import java.io.Serializable;

public class Grass extends GameObject  implements Serializable {
    ImageIcon grassIcon = new ImageIcon("grass.png");

    public ImageIcon getImageIcon() {
        return grassIcon;
    }
}
