import javax.swing.*;
//an option to select size of map
public class SizeSelector {
    private String heightchoosen;
    private String widthchoosen;
    private int height;
    private int width;


    public SizeSelector() {
        heightchoosen = JOptionPane.showInputDialog("Enter width of the map");
        widthchoosen = JOptionPane.showInputDialog("Enter heigh of the map");
        JOptionPane.showMessageDialog(null, "width:" + heightchoosen + "\n" + "height:" + widthchoosen, "message", JOptionPane.INFORMATION_MESSAGE);
        height = Integer.parseInt(heightchoosen) + 2;
        width = Integer.parseInt(widthchoosen) + 2;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
