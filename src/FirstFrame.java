import java.awt.*;
import java.awt.event.*;

/**
 * Created by Olga on 12.12.2014.
 */
public class FirstFrame extends Frame {

    FirstFrame(String title) {
        super(title);
    }

    public void paint(Graphics g) {
        g.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));
        g.drawString("Hello, World", 20, 100);
    }

    public static void main(String[] args) {
        Frame frame = new FirstFrame("Hello World");
        frame.setSize(400, 150);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
    }
}
