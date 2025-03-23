import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

//an Option to select new or load game
public class GameSelector extends JFrame {
    JPanel content;
    GameSelector(){
        starting();
    }
    public void starting() {
        setLocation(new Point( Toolkit.getDefaultToolkit().getScreenSize().width/2-200, Toolkit.getDefaultToolkit().getScreenSize().height/2-200));
        this.setSize(420, 230);
        this.setMaximumSize(new Dimension(420, 230));
        this.setMinimumSize(new Dimension(420, 230));
        content = (JPanel) this.getContentPane();
        content.setLayout(new GridLayout(2, 1));
        JButton newGame = new JButton();
        newGame.setIcon(new ImageIcon("NewGame.png"));
        JButton loadGame = new JButton();
        loadGame.setIcon(new ImageIcon("LoadGame.png"));
        newGame.setFont( new Font( "Serif", Font.BOLD, 28 ) );
        loadGame.setFont( new Font( "Serif", Font.BOLD, 28 ) );
        newGame.setBackground(Color.GRAY);
        loadGame.setBackground(Color.GRAY);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SizeSelector sizeSelector = new SizeSelector();
                GameFrame map = new GameFrame(1,sizeSelector.getWidth(),sizeSelector.getHeight(),0,new PowerUpParameters(1,1,4,false,false));
                map.setVisible(true);
                map.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                GameSelector.this.setVisible(false);
            }
        });
        loadGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                JFrame load=new JFrame();
                load.setSize(650,650);
                load.setLocation(650,300);
                int returnValue=jfc.showOpenDialog(null);
                if(returnValue==JFileChooser.APPROVE_OPTION){
                    File selectedFile=jfc.getSelectedFile();
                    try {
                        FileInputStream fis=new FileInputStream(selectedFile.getPath());
                        ObjectInputStream ois=new ObjectInputStream(fis);
                        GameFrame loadedgame=(GameFrame)ois.readObject();

                        ois.close();
                        loadedgame.setVisible(true);
                        loadedgame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                GameSelector.this.setVisible(false);
            }
        });

        content.add(newGame);
        content.add(loadGame);
    }
}
