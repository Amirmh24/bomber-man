import javax.swing.*;
import java.io.Serializable;
import java.util.Random;
//a type of powerUp which increases your speed
public class IncreaseSpeed extends PowerUp implements Serializable {
    ImageIcon powerUpIcon;
    Random random = new Random();

    public IncreaseSpeed() {
        int rand=random.nextInt(5);
        if(rand%5==0){
            powerUpIcon=new ImageIcon("powerUp1.png");
        }
        if(rand%5==1){
            powerUpIcon=new ImageIcon("powerUp2.png");
        }
        if(rand%5==2){
            powerUpIcon=new ImageIcon("powerUp3.png");
        }
        if(rand%5==3){
            powerUpIcon=new ImageIcon("powerUp4.png");
        }
        if(rand%5==4){
            powerUpIcon=new ImageIcon("powerUp5.png");
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
        if (gameHandler.getGameMemory().getPowerUpParameters().getBombermanSpeed() <= 8) {
            gameHandler.getGameMemory().getPowerUpParameters().setBombermanSpeed(gameHandler.getGameMemory().getPowerUpParameters().getBombermanSpeed() + 2);
            System.out.println("Your Speed Increased +");
        }
    }
}
