import javax.swing.*;
import java.io.Serializable;

//impossible to destroy and cross block for all beings except enemy(type4)
public class Block extends GameObject  implements Serializable {
    ImageIcon blockIcon = new ImageIcon("block.png");

    public ImageIcon getImageIcon() {
        return blockIcon;
    }
}
