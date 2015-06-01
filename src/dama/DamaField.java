package dama;

import java.awt.geom.Rectangle2D;


public class DamaField {
    
    private Rectangle2D.Double filedRectangle;
    public final int FILED_HIGHT = 70;
    public final int FILED_WIDTH = 70;
    private int x;
    private int y;
    
    

    public DamaField(int x, int y) {
        this.x = x;
        this.y = x;
          
       filedRectangle = new Rectangle2D.Double (x, y,FILED_HIGHT,FILED_WIDTH );
    }

    public Rectangle2D.Double getFiledRectangle() {
        
        return filedRectangle;
    }

    public int getFILED_HIGHT() {
        return FILED_HIGHT;
    }

    public int getFILED_WIDTH() {
        return FILED_WIDTH;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    
}
