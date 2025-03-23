import javax.swing.*;
import java.io.Serializable;

//
public class Wall extends GameObject  implements Serializable {
    ImageIcon wallIcon = new ImageIcon("wall.png");

    public ImageIcon getImageIcon() {
        return wallIcon;
    }
}
