import java.awt.*;
import java.awt.event.*;

/**
 * Created by Olga on 14.12.2014.
 */
public class MovingCircle2 extends Frame {

    private final int frameWidth = 500;
    private final int frameHeight = 500;
    private static int initX;
    private static int initY;
    private static final int robotSize = 20;
    private static int finishX;
    private static int finishY;
    private final int canvasWidth = frameWidth;
    private final int canvasHeight = 4 * frameHeight / 5;

    public MovingCircle2(String title) {
        super(title);
        setSize(frameWidth, frameHeight);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }});

        this.setLayout(new FlowLayout());
        Canvas canvas = new Canvas();
        canvas.setSize(canvasWidth, canvasHeight);
        canvas.setVisible(true);
        canvas.setBackground(Color.GRAY);
        this.add(canvas);

        Panel panel = new Panel();
        this.add(panel);
        Button move = new Button("Move");
        TextField valueX = new TextField("x", 5);
        TextField valueY = new TextField("y", 5);
        move.addActionListener(new MoveButtonListener(this, valueX, valueY));
        panel.add(move);
        panel.add(valueX);
        panel.add(valueY);
    }

    //Обработка нажатия на кнопку.
    public class MoveButtonListener implements ActionListener {
        private Frame parent;
        private TextField fieldX;
        private TextField fieldY;

        public MoveButtonListener(Frame parentFrame, TextField parentFieldX, TextField parentFieldY) {
            parent = parentFrame;
            fieldX = parentFieldX;
            fieldY = parentFieldY;
        }

        public void actionPerformed(ActionEvent event) {
            int x, y;
            try {
                x = Integer.parseInt(fieldX.getText());
                y = Integer.parseInt(fieldY.getText());
            } catch (NumberFormatException e) {
                x = 0;
                y = 0;
            }
            finishX = x;
            finishY = y;
            getFunction(finishX, finishY);
            try {
                parent.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Находит коээфициенты прямой между начальной и конечной точкой.
    public Point getFunction(int x1, int y1) {
        Point point = new Point();
        int x2 = initX;
        int y2 = initY;
        if (x1 > canvasWidth || x1 < 0) {
            x1 = 0;
        }
        if (y1 > canvasHeight || y1 < 0) {
            y1 = 0;
        }
        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - k * x1;
        point.setLocation(k, b);
        return point;
    }

    //Рассчитывание координат перерисовки, для анимации движения.
    public Point newXY(double x, double y) {
        int speed = 10;
        long elapsedTime = 0;
        int k2 = 0;
        int b2 = 0;
        Point point = new Point();
        Point functionPoint = getFunction(finishX, finishY);
        int k1 = (int) functionPoint.getX();
        int b1 = (int) functionPoint.getY();
        int cos = (int) (Math.abs(k1 * k2 + 1) / (Math.sqrt(1 + k1 * k1) * Math.sqrt(1 + k2 * k2)));
        long newTime = System.currentTimeMillis();
        x += speed * (newTime - elapsedTime) * cos;
        y = k1 * x + b1;
        point.setLocation(x, y);
        elapsedTime = newTime;
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
        MovingCircle2 frame = new MovingCircle2("Robot");
    }
}