import javax.swing.*;
import java.io.Serializable;
import java.util.Random;
//a type of powerUp which increases your bombs count
public class IncreaseBomb extends PowerUp implements Serializable {
    ImageIcon powerUpIcon;
    Random random = new Random();

    public IncreaseBomb() {
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
        if (gameHandler.getGameMemory().getPowerUpParameters().getPossibleBombCount()<=3) {
            gameHandler.getGameMemory().getPowerUpParameters().setPossibleBombCount(gameHandler.getGameMemory().getPowerUpParameters().getPossibleBombCount() + 1);
            System.out.println("Your Bombs Count Increased +");
        }
    }
}
