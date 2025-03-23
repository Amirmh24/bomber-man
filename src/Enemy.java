import java.io.Serializable;

//4clases(Enemies) extend this class
public class Enemy extends GameObject implements Serializable {
    private int speed;
    private int level;
    private boolean isAlive = true;
    ActionEnemy actionEnemy;

    public int getLevel() { return level; }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public ActionEnemy getActionEnemy() {
        return actionEnemy;
    }

    public void setActionEnemy(ActionEnemy actionEnemy) {
        this.actionEnemy = actionEnemy;
    }
}
