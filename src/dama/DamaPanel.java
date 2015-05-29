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

    private DamaField field , field1 ;

    private final int PANEL_HEIGHT = 600;
    private final int PANEL_WIDTH = 600;
    private Timer timer;

    public DamaPanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);
        
        field = new DamaField((PANEL_WIDTH - 560)/2,(PANEL_WIDTH - 560)/2 );
        field1 = new DamaField((PANEL_WIDTH - 560)/2 + 70,(PANEL_WIDTH - 560)/2 );
        

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2 = (Graphics2D) g;
        

        g2d.setPaint(Color.BLACK);
      
      

        g2d.fill(field.getFiledRectangle());
        

        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        

        g2d.draw(field.getFiledRectangle());
        g2d.draw(field1.getFiledRectangle());

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
