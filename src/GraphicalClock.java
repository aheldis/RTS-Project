import java.awt.*;
import java.awt.geom.Arc2D;
import java.time.LocalTime;
import java.time.ZoneId;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalClock extends JPanel implements Runnable {

    int xs, ys, xm, ym, xh, yh;
    Thread clockThread = new Thread(this);
    LocalTime time;

    int location_x, location_y;
    ZoneId zoneId;

    public GraphicalClock(LocalTime time, int id, ZoneId zoneId) {
        this.time = time;
        this.setDoubleBuffered(true);
        this.zoneId = zoneId;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocationXY((id - 2) * 300 + dim.width / 3 - this.getSize().width,
                dim.height / 3 - this.getSize().height);
        clockThread.start();
    }

    void setTime(LocalTime time) {
        this.time = time;
    }

    void setLocationXY(int x, int y) {
        this.location_x = x;
        this.location_y = y;
    }

    Thread getClockThread(){
        return clockThread;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        int side = getWidth() > getHeight() ? getHeight() : getWidth();
        g2d.scale(side / 250, side / 250);
        setAllRenderingHints(g2d);
        drawClockFace(g2d);
        drawClockHands(g2d);
    }

    @Override
    public void run() {
        JFrame f = new JFrame();
        f.setLocation(location_x, location_y);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300);
        f.add(this, BorderLayout.CENTER);
        f.setTitle(zoneId.toString());
        f.setVisible(true);

        while (true) {
            int s = time.getSecond();
            xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * 80 + 0);
            ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * 80 + 0);

            int m = time.getMinute();
            xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * 75 + 0);
            ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * 75 + 0);

            int h = time.getHour();
            xh = (int) (Math.cos((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 60 + 0);
            yh = (int) (Math.sin((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 60 + 0);
            repaint();
        }
    }

    private void setAllRenderingHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }

    private void drawClockFace(Graphics2D g) {
        g.setPaint(Color.white);
        g.fill(new Arc2D.Double(-110, -110, 220, 220, 0, 360, Arc2D.CHORD));
        g.setColor(Color.darkGray);
        g.setStroke(new BasicStroke(4.0f));
        g.draw(new Arc2D.Double(-110, -110, 220, 220, 0, 360, Arc2D.CHORD));

        for (int i = 0; i < 60; i++) {
            if ((i % 5) != 0) {
                g.setStroke(new BasicStroke(1.0f));
                g.setColor(Color.darkGray);
                g.drawLine(92, 0, 96, 0);
            } else {
                g.setColor(new Color(255, 22, 10));
                g.setStroke(new BasicStroke(2.0f));
                g.drawLine(88, 0, 96, 0);
            }
            g.rotate((Math.PI / 180.0) * 6.0);
        }
    }

    private void drawClockHands(Graphics2D g) {
        g.setColor(new Color(220, 22, 10));
        g.setStroke(new BasicStroke(5.0f));
        g.drawLine(0, 0, xh, yh);
        g.setStroke(new BasicStroke(3.0f));
        g.drawLine(0, 0, xm, ym);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2.0f));
        g.drawLine(0, 0, xs, ys);
        g.setColor(Color.black);
        g.fillOval(-5, -5, 10, 10);
        g.setColor(Color.white);
        g.fillOval(-2, -2, 4, 4);
    }

}