import java.io.Serializable;

//the game parameters which can change by powerUps
public class PowerUpParameters implements Serializable {
    private int bombRadius;
    private int possibleBombCount;
    private int bombermanSpeed;
    private boolean controlBomb;
private boolean ghostMode;
    public PowerUpParameters(int bombRadius, int possibleBombCount, int bombermanSpeed, boolean controlBomb,boolean ghostMode) {
        this.bombRadius = bombRadius;
        this.possibleBombCount = possibleBombCount;
        this.bombermanSpeed = bombermanSpeed;
        this.controlBomb = controlBomb;
        this.ghostMode=ghostMode;
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public void setBombRadius(int bombRadius) {
        this.bombRadius = bombRadius;
    }

    public int getPossibleBombCount() {
        return possibleBombCount;
    }

    public void setPossibleBombCount(int possibleBombCount) {
        this.possibleBombCount = possibleBombCount;
    }

    public int getBombermanSpeed() {
        return bombermanSpeed;
    }

    public void setBombermanSpeed(int bombermanSpeed) {
        this.bombermanSpeed = bombermanSpeed;
    }

    public boolean isControlBomb() {
        return controlBomb;
    }

    public void setControlBomb(boolean controlBomb) {
        this.controlBomb = controlBomb;
    }

    public boolean isGhostMode() {
        return ghostMode;
    }

    public void setGhostMode(boolean ghostMode) {
        this.ghostMode = ghostMode;
    }
}
