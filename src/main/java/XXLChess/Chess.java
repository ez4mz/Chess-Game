package XXLChess;

import XXLChess.util.Target;
import XXLChess.util.Distance;
import XXLChess.util.Point;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Chess implements Cloneable{

    protected boolean isWhite;

    protected Point point;

    protected int pix = 48;

    protected int img_x;

    protected int img_y;

    private PImage sprite;

    protected double value;

    private boolean isKing;

    public Chess(int x, int y, boolean isWhite, PImage sprite) {
        this.isWhite = isWhite;
        this.sprite = sprite;
        this.point = new Point(x, y);
        this.img_x = x * pix;
        this.img_y = y * pix;
        this.isKing = false;
        this.value = 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    public void setPix(int pix) {
        this.pix = pix;
    }

    public void draw(PApplet app) {
        app.image(this.sprite, this.img_x, this.img_y);
    }

    //Identify all possible moves and excludes those that would leave the King in check, only keep the safe moves.
    public List<Target> getLocation(Board board) {
        List<Target> ls = new ArrayList<>();
        List<Target> movableLocations = getLocationWithoutCheck(board);
        if (!board.check(isWhite)) {
            ls = getLocationWithoutCheck(board);
        } else {
            for(Target aim: movableLocations) {
                Board cloned_board;
                try {
                    cloned_board = board.clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                Chess cloned_chess = cloned_board.findChess(point.getX(),point.getY());
                cloned_board.move(cloned_chess, aim);
                if(!cloned_board.check(isWhite)){
                    ls.add(aim);
                }
            }
        }
        return ls;
    }

    //Identify all possible locations where the piece can move without considering whether the king is in check or not.
    public List<Target> getLocationWithoutCheck(Board board) {
        List<Point> ls = getPotentialMoves(board);
        List<Target> aims = new ArrayList<>();
        for (Point point : ls) {
            if (point.getX() < board.getWidth() && point.getY() < board.getLength() && point.getX() >= 0 && point.getY() >= 0) {
                Chess found = board.findChess(point.getX(), point.getY());
                Target aim;
                if (found == null) {
                    aim = new Target(point, false);
                    aims.add(aim);
                } else if (found.isWhite() != isWhite) {
                    aim = new Target(point, true);
                    aims.add(aim);
                }
            }
        }
        return aims;
    }

    /*
    Return a list of Point objects that represent potential locations the chess piece could move to.
    The specific logic of movement would depend on the type of chess pieces, so this method is abstract.
     */
    public abstract List<Point> getPotentialMoves(Board board);

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public boolean isWhite() {
        return isWhite;
    }

    /*
    Smoothly animating the movement of a piece from its current position to a new position.
    This is achieved by updating the img_x and img_y instance variables.
     */
    public boolean tick(double piece_movement_speed, double max_movement_time, int FPS) {
        int x = point.getX();
        int y = point.getY();
        if(x*48 == img_x && y*48 == img_y) {
            return false;
        }
        double distance = Distance.distance(x, y, img_x, img_y);
        double speed = piece_movement_speed;
        double time = distance / (piece_movement_speed * FPS);
        if (time > max_movement_time) {
            speed = distance / (time * FPS);
        }
        int pix_x = x * 48;
        int pix_y = y * 48;
        if (img_x != pix_x) {
            if (img_x > pix_x) {
                img_x -= speed;
            } else {
                img_x += speed;
            }
        }
        if (img_y != pix_y) {
            if (img_y > pix_y) {
                img_y -= speed;
            } else {
                img_y += speed;
            }
        }
        if (Math.abs(img_x - pix_x) < speed) {
            img_x = pix_x;
        }
        if (Math.abs(img_y - pix_y) < speed) {
            img_y = pix_y;
        }
        return true;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point){
        this.point = point;
    }

    public int getImg_x() {
        return img_x;
    }

    public int getImg_y() {
        return img_y;
    }

    //Create and return a copy of the current Chess object
    @Override
    public Chess clone() throws CloneNotSupportedException {
        return (Chess) super.clone();
    }
}
