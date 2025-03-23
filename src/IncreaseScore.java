import javax.swing.*;
import java.io.Serializable;
import java.util.Random;
//a type of powerUp which increases your score 100points;
public class IncreaseScore extends PowerUp implements Serializable {

    ImageIcon powerUpIcon;
    Random random = new Random();

    public IncreaseScore() {
        int rand = random.nextInt(5);
        if (rand % 5 == 0) {
            powerUpIcon = new ImageIcon("powerUp1.png");
        }
        if (rand % 5 == 1) {
            powerUpIcon = new ImageIcon("powerUp2.png");
        }
        if (rand % 5 == 2) {
            powerUpIcon = new ImageIcon("powerUp3.png");
        }
        if (rand % 5 == 3) {
            powerUpIcon = new ImageIcon("powerUp4.png");
        }
        if (rand % 5 == 4) {
            powerUpIcon = new ImageIcon("powerUp5.png");
        }
    }

    public ImageIcon getImageIcon() {
        if (super.isBroken()) {
            return powerUpIcon;
        }
        return super.getWallIcon();
    }

    @Override
    public void doAction(GameHandler gameHandler) {
        gameHandler.getGameMemory().score = gameHandler.getGameMemory().score + 100;
        System.out.println("Your Score Increased +");
    }
}