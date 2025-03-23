import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

//An option to play again current level or exit game when you losed
public class TryAgain extends JFrame implements Serializable {
    JPanel content;
    GameFrame gameFrame;

    TryAgain(GameFrame gameFrame) {
        this.setVisible(true);
        this.gameFrame = gameFrame;
        starting();
    }

    public void starting() {
        setLocation(new Point(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200));
        this.setSize(420, 230);
        this.setMaximumSize(new Dimension(420, 230));
        this.setMinimumSize(new Dimension(420, 230));
        content = (JPanel) this.getContentPane();
        content.setLayout(new GridLayout(2, 1));

        JButton newGame = new JButton();
        newGame.setIcon(new ImageIcon("TryAgain.png"));
        JButton exitGame = new JButton();
        exitGame.setIcon(new ImageIcon("ExitGame.png"));
        newGame.setFont(new Font("Serif", Font.BOLD, 28));
        exitGame.setFont(new Font("Serif", Font.BOLD, 28));
        newGame.setBackground(Color.GRAY);
        exitGame.setBackground(Color.GRAY);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                GameFrame newGameFrame = new GameFrame(gameFrame.LEVEL, gameFrame.width, gameFrame.height,0,new PowerUpParameters(1,1,4,false,false));
                TryAgain.this.setVisible(false);
                newGameFrame.setVisible(true);
            }
        });
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        content.add(newGame);
        content.add(exitGame);
    }
}
