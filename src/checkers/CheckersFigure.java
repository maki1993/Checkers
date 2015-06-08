package checkers;

import java.awt.geom.Ellipse2D;

public class CheckersFigure {

    private Ellipse2D.Double figure;

    public final int FIGURE_HIGHT = 40;
    public final int FIGURE_WIDTH = 40;
    private int x;
    private int y;

    public CheckersFigure(int x, int y) {
        this.x = x;
        this.y = y;

        figure = new Ellipse2D.Double(x, y, FIGURE_HIGHT, FIGURE_WIDTH);
    }

    public Ellipse2D.Double getFigure() {
        return figure;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFIGURE_HIGHT() {
        return FIGURE_HIGHT;
    }

    public int getFIGURE_WIDTH() {
        return FIGURE_WIDTH;
    }

}
