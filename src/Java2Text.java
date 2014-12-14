import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.event.*;

/**
 * Created by Olga on 13.12.2014.
 */
public class Java2Text extends Frame {

    public Java2Text(String title) {
        super(title);
        setSize(400, 200);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        int w = getSize().width;
        int h = getSize().height;
        FontRenderContext frc = g.getFontRenderContext();
        String s = "Shadow";
        Font f = new Font("Serif", Font.BOLD, h / 3);
        TextLayout tl = new TextLayout(s, f, frc);
        AffineTransform at = new AffineTransform();
        at.setToTranslation(w / 2 - tl.getBounds().getWidth() / 2, h / 2);
        Shape sh = tl.getOutline(at);

        g.draw(sh);

        AffineTransform atsh = new AffineTransform(1, 0.0, 1.5, -1, 0.0, 0.0);
        g.transform(at);
        g.transform(atsh);
        Font df = f.deriveFont(atsh);
        TextLayout dtl = new TextLayout(s, df, frc);
        Shape sh2 = dtl.getOutline(atsh);

        g.fill(sh2);
    }

    public static void main(String[] args) {
        Java2Text frame = new Java2Text("Text's Shadow");
    }
}
