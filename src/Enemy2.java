import javax.swing.*;
import java.io.Serializable;

public class Enemy2 extends Enemy implements Serializable {
    private String direction = "down";
    private ImageIcon enemy2DownIcon = new ImageIcon("enemy2down.png");
    private ImageIcon enemy2UpIcon = new ImageIcon("enemy2up.png");
    private ImageIcon enemy2RightIcon = new ImageIcon("enemy2right.png");
    private ImageIcon enemy2LeftIcon = new ImageIcon("enemy2left.png");
    private ActionEnemy2 actionEnemy2;

    public Enemy2(GameHandler paintPanel, Location location) {
        super.setLocation(location);
        actionEnemy2 = new ActionEnemy2(paintPanel, this);
        actionEnemy2.moveTimer.start();
    }

    @Override
    public int getLevel() {
        return 2;
    }

    public ImageIcon getImageIcon() {
        if (direction == "up") {
            return enemy2UpIcon;
        }
        if (direction == "down") {
            return enemy2DownIcon;
        }
        if (direction == "right") {
            return enemy2RightIcon;
        }
        if (direction == "left") {
            return enemy2LeftIcon;
        }
        return enemy2DownIcon;
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
    public ActionEnemy2 getActionEnemy() {
        return actionEnemy2;
    }

    @Override
    public void setActionEnemy(ActionEnemy actionEnemy) {
        super.actionEnemy = actionEnemy;
        this.actionEnemy2 = (ActionEnemy2) actionEnemy;
    }

}
