import java.awt.*;
import java.awt.event.*;

/**
 * Created by Olga on 13.12.2014.
 */
public class MovingCircle extends Frame {

    private static int initX;
    private static int initY;
    private static final int robotSize = 20;

    public MovingCircle(String title) {
        super(title);
        setSize(400, 400);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent event){
            System.exit(0);
        }
        });
        initX = this.getWidth() / 2;
        initY = this.getHeight() / 2;
    }

    public Point newXY(double x, double y) {
        int interval = 1;
        Point point = new Point();
        x += interval;
        y = x;
        point.setLocation(x, y);
        return point;
    }

    public void paint(Graphics g) {
        double x;
        double y;
        Point point = newXY(initX, initY);
        x = point.getX();
        y = point.getY();
        g.fillOval((int) x - 10, (int) y - 10, robotSize, robotSize);
        initX = (int) x;
        initY = (int) y;
    }

    public static void main(String[] args) {
        MovingCircle frame = new MovingCircle("Robot");
        int finishX = frame.getWidth();
        int finishY = frame.getHeight();
        while (initX <= finishX - robotSize && initY <= finishY - robotSize) {
            try {
                frame.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
