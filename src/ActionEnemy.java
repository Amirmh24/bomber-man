import javax.swing.*;
import java.io.Serializable;

//4clases(Actions of each enemies) extend this class
public class ActionEnemy extends Thread implements Serializable {
    Timer moveTimer;
    Enemy enemy;

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
