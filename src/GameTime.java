import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

//game timer (defult:5 min)
public class GameTime implements Serializable {
    private int time=300;
    private GameFrame gameFrame;
    private Timer timer=new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(time>0) {
                time--;
            }else {
                gameFrame.gameHandler.getGameMemory().score--;
            }
            if(time<=0 && gameFrame.gameHandler.getGameMemory().score<=0){
                gameFrame.gameHandler.getGameMemory().stopGame();
                new TryAgain(gameFrame);
            }
            gameFrame.infoPanel.repaint();
        }
    });;

    public GameTime(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        timer.start();
    }

    public String toString() {
        return ((time/600)+""+((time/60)%10)+":"+((time%60)/10)+""+((time%60)%10));
    }

    public Timer getTimer() {
        return timer;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
