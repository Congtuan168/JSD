package tut06.japaneseflag;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class JapaneseFlag extends JPanel {
    public JapaneseFlag () {
        setBackground(Color.white);
    }
    @Override
    public void paintComponent(Graphics graphics2D) {
        super.paintComponent(graphics2D);
        Graphics2D g = (Graphics2D) graphics2D;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(188, 0, 45));
        Shape redCircle = new Ellipse2D.Double((getWidth()-200)/2, (getHeight()-200)/2, 200, 200);
        g.fill(redCircle);
        g.setStroke(new BasicStroke(3));
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Japan Flag");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JapaneseFlag());

        frame.setSize(750, 600);
        frame.setVisible(true);

    }
}
