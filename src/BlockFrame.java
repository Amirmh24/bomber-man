import javax.swing.*;
import java.io.Serializable;

//impossible to destroy and cross blockFrame for all beings all the time
//objects of first and last rows and columns of man are instances of blockFrame
public class BlockFrame extends GameObject  implements Serializable {
    ImageIcon blockFrameIcon = new ImageIcon("block.png");

    public ImageIcon getImageIcon() {
        return blockFrameIcon;
    }
}
