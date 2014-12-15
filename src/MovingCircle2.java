import java.awt.*;
import java.awt.event.*;

/**
 * Created by Olga on 14.12.2014.
 */
public class MovingCircle2 extends Frame {

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 500;
    private static int initX;
    private static int initY;
    private static final int ROBOT_SIZE = 20;
    private static int finishX;
    private static int finishY;
    private final int CANVAS_WIDTH = FRAME_WIDTH;
    private final int CANVAS_HEIGHT = 4 * FRAME_HEIGHT / 5;
    private final int SPEED = 10;
    long prevTime;
    long currentTime;
    long time;

    public MovingCircle2(String title) {
        super(title);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }});

        this.setLayout(new BorderLayout());
        Field field = new Field(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.add(field, BorderLayout.NORTH);

        Panel panel = new Panel();
        this.add(panel, BorderLayout.SOUTH);
        Button move = new Button("Move");
        TextField valueX = new TextField("x", 5);
        TextField valueY = new TextField("y", 5);
        move.addActionListener(new MoveButtonListener(this, field, valueX, valueY));
        panel.add(move);
        panel.add(valueX);
        panel.add(valueY);
    }

    //Обработка нажатия на кнопку.
    public class MoveButtonListener implements ActionListener {
        private Frame parent;
        private TextField fieldX;
        private TextField fieldY;
        private Field canvas;

        public MoveButtonListener(Frame parentFrame, Field parentCanvas, TextField parentFieldX, TextField parentFieldY) {
            parent = parentFrame;
            fieldX = parentFieldX;
            fieldY = parentFieldY;
            canvas = parentCanvas;
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
            if (x > CANVAS_WIDTH || x < 0) {
                x = 0;
            }
            if (y > CANVAS_HEIGHT || y < 0) {
                y = 0;
            }
            finishX = x;
            finishY = y;
            prevTime = System.currentTimeMillis();
            canvas.repaint();
        }
    }

    //Рассчитывание координат перерисовки, для анимации движения.
    public void newXY() {
        int vectorX = finishX - initX;
        int vectorY = finishY - initY;
        double vectorLength = Math.sqrt(Math.pow(finishX - initX, 2) + Math.pow(finishY - initY, 2));
        double unitVectorX = vectorX / vectorLength;
        double unitVectorY = vectorY / vectorLength;
        double x = SPEED * time * unitVectorX;
        double y = SPEED * time * unitVectorY;
        initX += x;
        initY += y;
    }

    public class Field extends Canvas {

        public Field(int width, int height) {
            super();
            setSize(width, height);
            setVisible(true);
            setBackground(Color.GRAY);
        }

        public void paint(Graphics g) {
            currentTime = System.currentTimeMillis();
            time = currentTime - prevTime;
            newXY();
            g.fillOval(initX - 10, initY - 10, ROBOT_SIZE, ROBOT_SIZE);
            prevTime = currentTime;
        }
    }

    public static void main(String[] args) {
        MovingCircle2 frame = new MovingCircle2("Robot");
        while (initX != finishX && initY != finishY) {
            try {
                frame.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}