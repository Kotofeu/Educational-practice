import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
public class Main extends JFrame {
    Main(String s) {
        super(s);
        setLayout(null);
        setSize(600, 750);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D gr = (Graphics2D)g;
        g.fillRect(50, 300, 400, 400);

        g.setColor(Color.WHITE);
        g.fillRect(100, 350, 300, 300);

        int px1[]={50,450,550,200,50};
        int py1[]={300,300,240,240, 300};
        g.setColor(Color.GRAY);
        g.fillPolygon(px1,py1,4);

        int px2[]={450,550,550,450,450};
        int py2[]={300,240,600,700, 300};
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(px2,py2,4);
        BasicStroke pen;
        pen=new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        ((Graphics2D) g).setStroke(pen);


        g.setColor(Color.WHITE);
        g.drawOval(250, 250, 100, 40);

        g.setColor(Color.BLACK);
        g.drawLine(300, 270, 270,150);
        g.fillOval(260, 140, 20, 20);

        g.drawLine(300, 270, 330,150);
        g.fillOval(320, 140, 20, 20);
    }

    public static void main(String[] args) {
        new Main("Телевизор");
    }
}