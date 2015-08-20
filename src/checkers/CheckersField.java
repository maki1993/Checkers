package checkers;

import java.awt.geom.Rectangle2D;

public class CheckersField {

    private Rectangle2D.Double filedRectangle;
   
    public  final int FILED_DIMENSION = 70;
    private int x;
    private int y;

    public CheckersField(int x, int y) {
        this.x = x;
        this.y = x;

        filedRectangle = new Rectangle2D.Double(x, y, FILED_DIMENSION, FILED_DIMENSION);
    }

    public Rectangle2D.Double getFiledRectangle() {

        return filedRectangle;
    }

    public int getFILED_DIMENSION() {
        return FILED_DIMENSION;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
