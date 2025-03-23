import javax.swing.*;
import java.io.Serializable;

public class Enemy1 extends Enemy implements Serializable {
    private String direction = "down";
    private ImageIcon enemy1DownIcon = new ImageIcon("enemy1down.png");
    private ImageIcon enemy1UpIcon = new ImageIcon("enemy1up.png");
    private ImageIcon enemy1RightIcon = new ImageIcon("enemy1right.png");
    private ImageIcon enemy1LeftIcon = new ImageIcon("enemy1left.png");
    private ActionEnemy1 actionEnemy1;

    public Enemy1(GameHandler paintPanel, Location location) {
        super.setLocation(location);
        actionEnemy1 = new ActionEnemy1(paintPanel, this);
        actionEnemy1.moveTimer.start();
    }

    @Override
    public int getLevel() {
        return 1;
    }

    public ImageIcon getImageIcon() {
        if (direction == "up") {
            return enemy1UpIcon;
        }
        if (direction == "down") {
            return enemy1DownIcon;
        }
        if (direction == "right") {
            return enemy1RightIcon;
        }
        if (direction == "left") {
            return enemy1LeftIcon;
        }
        return enemy1DownIcon;
    }

    public int getX() {
        return super.getLocation().getX();
    }

    public void setX(int x) {
        super.getLocation().setX(x);
    }

    public int getY() {
        return super.getLocation().getY();
    }

    public void setY(int y) { super.getLocation().setY(y); }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public ActionEnemy1 getActionEnemy() {
        return actionEnemy1;
    }

    @Override
    public void setActionEnemy(ActionEnemy actionEnemy) {
        super.actionEnemy = actionEnemy;
        this.actionEnemy1 = (ActionEnemy1)actionEnemy;
    }
}