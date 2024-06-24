package XXLChess.util;

/*
The 'Target' class represents a potential move for a piece on the board,including the destination (Point) and whether
that move involves capturing an opponent's piece (capture).
 */

public class Target {
    private Point point;
    private boolean capture;

    //棋子的目标位置和是否捕获
    public Target(Point point, boolean capture) {
        this.point = point;
        this.capture = capture;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }
}
