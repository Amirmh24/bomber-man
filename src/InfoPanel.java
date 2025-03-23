import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

//bottom panel shows information of game
public class InfoPanel extends JPanel implements Serializable {
    private GameFrame gameFrame;
    private JLabel infoLabel = new JLabel();

    public InfoPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(gameFrame.width, 30));
        this.add(infoLabel, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (gameFrame.gameHandler.getGameMemory().getBomberman().isAlive()) {
            infoLabel.setText("Level: " + gameFrame.LEVEL + "                              Score: " + gameFrame.gameHandler.getGameMemory().score + "                              Time: " + gameFrame.gameTime.toString());
        }else {
            this.setBackground(Color.WHITE);
            infoLabel.setText("Qame Over");
        }
    }
}
