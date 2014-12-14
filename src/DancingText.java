import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * Created by Olga on 13.12.2014.
 */
public class DancingText extends Frame {

    public DancingText() {
        super();
        setSize(400, 200);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
        });
    }

    public void paint(Graphics gr) {
        int h = 5;
        Graphics2D g = (Graphics2D) gr;
        FontRenderContext frc = g.getFontRenderContext();
        Font f = new Font("Serif", Font.BOLD, 30);
        GlyphVector gv = f.createGlyphVector(frc, "Dancing Text");
        int len = gv.getNumGlyphs();

        for (int i = 0; i < len; i++) {
            Point2D.Double p = new Point2D.Double(25 * i, h = -h);
            gv.setGlyphPosition(i, p);
        }

        g.drawGlyphVector(gv, 10, 100);
    }

    public static void main(String[] args) {
        DancingText frame = new DancingText();
    }

}
