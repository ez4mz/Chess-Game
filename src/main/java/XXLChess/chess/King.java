package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class King extends Chess {

    public King(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value= 100;
    }

    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<Point>();
        int x = point.getX();
        int y = point.getY();

        ls.add(new Point(x - 1, y - 1)); // Top left
        ls.add(new Point(x, y - 1));     // Top
        ls.add(new Point(x + 1, y - 1)); // Top right
        ls.add(new Point(x - 1, y));     // Left
        ls.add(new Point(x + 1, y));     // Right
        ls.add(new Point(x - 1, y + 1)); // Bottom left
        ls.add(new Point(x, y + 1));     // Bottom
        ls.add(new Point(x + 1, y + 1)); // Bottom right

        return ls;
    }

}
