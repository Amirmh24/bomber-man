import javax.swing.*;
import java.io.Serializable;

public class Enemy3 extends Enemy implements Serializable {
    private String direction = "down";
    private ImageIcon enemy3DownIcon = new ImageIcon("enemy3down.png");
    private ImageIcon enemy3UpIcon = new ImageIcon("enemy3up.png");
    private ImageIcon enemy3RightIcon = new ImageIcon("enemy3right.png");
    private ImageIcon enemy3LeftIcon = new ImageIcon("enemy3left.png");
    private ActionEnemy3 actionEnemy3;

    public Enemy3(GameHandler paintPanel, Location location) {
        super.setLocation(location);
        actionEnemy3 = new ActionEnemy3(paintPanel, this);
        actionEnemy3.moveTimer.start();
    }

    @Override
    public int getLevel() {
        return 3;
    }

    public ImageIcon getImageIcon() {
        if (direction == "up") {
            return enemy3UpIcon;
        }
        if (direction == "down") {
            return enemy3DownIcon;
        }
        if (direction == "right") {
            return enemy3RightIcon;
        }
        if (direction == "left") {
            return enemy3LeftIcon;
        }
        return enemy3DownIcon;
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
    public ActionEnemy3 getActionEnemy() {
        return actionEnemy3;
    }

    @Override
    public void setActionEnemy(ActionEnemy actionEnemy) {
        super.actionEnemy = actionEnemy;
        this.actionEnemy3 = (ActionEnemy3) actionEnemy;
    }

}
