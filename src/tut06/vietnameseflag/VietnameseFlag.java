package tut06.vietnameseflag;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class VietnameseFlag extends JPanel {
    public VietnameseFlag() {
        setBackground(new Color(218, 37, 29));
    }

    @Override
    public void paintComponent(Graphics graphics2D) {
        super.paintComponent(graphics2D);

        Graphics2D g = (Graphics2D) graphics2D;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setColor(Color.red);
//
//        Shape shape = createDefaultStar(50, 200, 200);
//        g.fill(shape);
        // Calculate the center of the panel
        int width = getWidth();
        int height = getHeight();
        double starRadius = 50;
        double centerX = width / 2.0;
        double centerY = height / 2.0;

        // Draw the star in the center
        Shape shape = createDefaultStar(starRadius, centerX, centerY);
        g.setColor(new Color(255, 255, 0)); // Set the star color to yellow
        g.fill(shape);
    }

    private Path2D createDefaultStar(double radius, double centerX,
                                           double centerY)
    {
        return createStar(centerX, centerY, radius, radius * 2.63, 5,
                Math.toRadians(-18));
    }
    public Path2D createStar(double centerX, double centerY, double innerRadius, double outerRadius, int numRays, double starAngleRad) {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++) {
            double angleRad = starAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0) {
                relX *= outerRadius;
                relY *= outerRadius;
            } else {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0) {
                path.moveTo(centerX + relX, centerY + relY);
            } else {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Vietnam Flag");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(750, 600);
        jFrame.add(new VietnameseFlag());
        jFrame.setVisible(true);
    }
}
