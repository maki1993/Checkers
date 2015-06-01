package dama;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DamaPanel extends JPanel implements ActionListener, KeyListener {

    private DamaField field, field1;
    private DamaFigure figure;

    private final int PANEL_HEIGHT = 600;
    private final int PANEL_WIDTH = 600;
    
    private Timer timer;

    public DamaPanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);

        field = new DamaField((PANEL_WIDTH - 560) / 2, (PANEL_HEIGHT - 560) / 2);
       
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        

        int x = field.getX();
        int y = field.getY();
       
        int i, j;

        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {

                field1 = new DamaField(x, y);
                figure = new DamaFigure(x+15, y+15);
                if (i % 2 == j % 2) {

                    g2d.setColor(Color.BLACK);
                    g2d.fill(field1.getFiledRectangle());
                    g2d.setPaint(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));

                    g2d.draw(field1.getFiledRectangle());
                    x += field.getFILED_WIDTH();

                    if (i == 1 || i == 2 && i % 2 == j % 2) {
                        g2d.setColor(Color.RED);
                        
                    } else if (i == 7 || i == 8 && i % 2 == j % 2) {
                        g2d.setColor(Color.BLUE);
                    }

                    g2d.fill(figure.getFigure());

                    g2d.setPaint(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));

                    g2d.draw(figure.getFigure());

                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.fill(field1.getFiledRectangle());
                    g2d.setPaint(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));

                    g2d.draw(field1.getFiledRectangle());
                    x += field.getFILED_WIDTH();
                }

            }
            y += field.getFILED_WIDTH();
            x = field.getX();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
