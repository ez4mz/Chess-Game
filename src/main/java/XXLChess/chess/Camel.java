package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Camel extends Chess {

    public Camel(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value = 2;
    }
    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<Point>();
        int x = point.getX();
        int y = point.getY();

        // Add camel moves
        ls.add(new Point(x - 3, y - 1));  // Up-Left
        ls.add(new Point(x - 3, y + 1));  // Up-Right
        ls.add(new Point(x + 3, y - 1));  // Down-Left
        ls.add(new Point(x + 3, y + 1));  // Down-Right
        ls.add(new Point(x - 1, y - 3));  // Left-Up
        ls.add(new Point(x + 1, y - 3));  // Right-Up
        ls.add(new Point(x - 1, y + 3));  // Left-Down
        ls.add(new Point(x + 1, y + 3));  // Right-Down

        return ls;
    }
}
