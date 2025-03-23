import javax.swing.*;
import java.io.Serializable;

public class Enemy4 extends Enemy implements Serializable {
    private String direction = "down";
    private ImageIcon enemy4DownIcon = new ImageIcon("enemy4down.png");
    private ImageIcon enemy4UpIcon = new ImageIcon("enemy4up.png");
    private ImageIcon enemy4RightIcon = new ImageIcon("enemy4right.png");
    private ImageIcon enemy4LeftIcon = new ImageIcon("enemy4left.png");
    private ActionEnemy4 actionEnemy4;

    public Enemy4(GameHandler paintPanel, Location location) {
        super.setLocation(location);
        actionEnemy4 = new ActionEnemy4(paintPanel, this);
        actionEnemy4.moveTimer.start();
    }

    @Override
    public int getLevel() {
        return 4;
    }

    public ImageIcon getImageIcon() {
        if (direction == "up") {
            return enemy4UpIcon;
        }
        if (direction == "down") {
            return enemy4DownIcon;
        }
        if (direction == "right") {
            return enemy4RightIcon;
        }
        if (direction == "left") {
            return enemy4LeftIcon;
        }
        return enemy4DownIcon;
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
    public ActionEnemy4 getActionEnemy() {
        return actionEnemy4;
    }

    @Override
    public void setActionEnemy(ActionEnemy actionEnemy) {
        super.actionEnemy = actionEnemy;
        this.actionEnemy4 = (ActionEnemy4) actionEnemy;
    }

}
