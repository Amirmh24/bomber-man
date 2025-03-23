import java.io.Serializable;

public class PowerUp extends BehindWallObjects implements Serializable {

    public boolean isBroken() {
        return super.isBroken();
    }

    public void setBroken(boolean broken) {
        super.setBroken(broken);
    }

}
