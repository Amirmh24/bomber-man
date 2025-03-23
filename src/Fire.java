import javax.swing.*;
import java.io.Serializable;

public class Fire extends GameObject implements Serializable {
    private ImageIcon explosionIcon;
    private String direction;
    public Fire(String string) {
        if(string=="center"){
            explosionIcon= new ImageIcon("explosionCenter.jpg");
        }
        if(string=="up"){
            explosionIcon= new ImageIcon("explosionUp.jpg");
        }
        if(string=="down"){
            explosionIcon= new ImageIcon("explosionDown.jpg");
        }
        if(string=="right"){
            explosionIcon= new ImageIcon("explosionRight.jpg");
        }
        if(string=="left"){
            explosionIcon= new ImageIcon("explosionLeft.jpg");
        }
        if(string=="bang"){
            explosionIcon= new ImageIcon("bang.png");
        }
        direction=string;
    }
    public ImageIcon getImageIcon() {
        return explosionIcon;
    }

    public String getDirection() {
        return direction;
    }
}
