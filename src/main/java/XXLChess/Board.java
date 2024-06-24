package XXLChess;

import XXLChess.util.Target;
import XXLChess.util.Color;
import XXLChess.util.Point;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable {
    private final int width;
    private final int length;
    private int squareSize;
    private List<Chess> pieceList;

    private boolean isChecked_white;

    private boolean isChecked_black;


    public Board(int width, int length, int squareSize, List<Chess> pieceList) {
        this.width = width;
        this.length = length;
        this.squareSize = squareSize;
        this.pieceList = pieceList;
    }

    public Board() {
        this.width = 14;
        this.length = 14;
        this.pieceList = new ArrayList<>();
    }

    public Board(List<Chess> pieceList) {
        this.width = 14;
        this.length = 14;
        this.pieceList = pieceList;
    }

    public Board(int squareSize, List<Chess> pieceList) {
        this.width = 14;
        this.length = 14;
        this.squareSize = squareSize;
        this.pieceList = pieceList;
    }

    //Identify if a king of a certain color is in check
    public boolean check(boolean is_white) {
        Point kingPoint = getKingLocation(is_white);
        /*
          For each piece, if the piece's color does not match the king's color (meaning it is an opponent's piece),
          the method generates a list of all potential moves that piece could make.
         */
        for (Chess chess : pieceList) {
            if (chess.isWhite() != is_white) {
                List<Target> targets = chess.getLocationWithoutCheck(this);
                /*
                Check each potential move to see if any would result in the piece being in the same location as
                'kingPoint' which indicates that the piece could capture the king on its next move.
                 */
                for (Target target : targets) {
                    if (target.getPoint().equals(kingPoint)) {
                        return true;
                    }
                }
            }
        }
        //If no such move is found after checking all pieces, then the king is safe
        return false;
    }

    //Identify if a king of a certain color is in checkmate
    public boolean checkMate(boolean is_white) {
        for (Chess chess : pieceList) {
            /*
            If any piece of the specified color has at least one potential move, the method returns false, indicating
            that the king is not in checkmate, since there's a move that may potentially get the king out of check.
             */
            if (chess.isWhite() == is_white && chess.getLocation(this).size() != 0) {
                return false;
            }
        }
        //If no such move is found after checking all the pieces, then the king is in checkmate.
        return true;
    }


    //Identify all pieces that contribute to check
    public List<Chess> allCheckingPieces(boolean is_white) {
        Point kingPoint = getKingLocation(is_white);
        //Create an empty list which will hold all the pieces that are currently checking the king.
        List<Chess> checkingPieces = new ArrayList<>();
        for (Chess chess : pieceList) {
            //For each piece, if belongs to the opponent and is not a king
            if (chess.isWhite() != is_white && !chess.isKing()) {
                //Generate a list of all potential moves this piece could make without putting its own king in check.
                List<Target> targets = chess.getLocationWithoutCheck(this);
                for (Target target : targets) {
                    //Check each potential move to see if it would result in the king being captured
                    if (target.getPoint().equals(kingPoint)) {
                        //If such a move exists, the piece is added to the list
                        checkingPieces.add(chess);
                        break;
                    }
                }
            }
        }
        return checkingPieces;
    }

    public void updateCheckStatus() {
        isChecked_white = check(true);
        isChecked_black = check(false);
    }

    //Draw the chessboard on the screen
    public void drawBoard(PApplet app) {
        int squareSize = 48;
        app.noStroke();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                app.fill((i + j) % 2 == 0 ? Color.LIGHT_BROWN : Color.DARK_BROWN);
                app.rect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }
        app.fill(Color.GRAY);
        app.rect(width * squareSize, 0, 120, length * squareSize);
    }

    //Find a specific chess piece on the board
    public Chess findChess(int x, int y) {
        for (Chess chess : pieceList) {
            if (chess.getX() == x && chess.getY() == y) {
                return chess;
            }
        }
        return null;
    }

    //Draw all the pieces on the board
    public void drawChess(PApplet app) {
        for (Chess chess : pieceList) {
            chess.draw(app);
        }
    }


    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public List<Chess> getPieceList() {
        return pieceList;
    }

    public void addChess(Chess chess) {
        pieceList.add(chess);
    }

    public void removeChess(Point point) {
        for (Chess chess : pieceList) {
            if (chess.getPoint().equals(point)) {
                pieceList.remove(chess);
                break;
            }
        }
    }

    public Point getKingLocation(boolean isWhite) {
        for (Chess chess : pieceList) {
            if (chess.isKing() && chess.isWhite() == isWhite) {
                return chess.getPoint();
            }
        }
        return null;
    }

    public void move(Chess chess, Target aim) {
        if (aim.isCapture()) {
            removeChess(aim.getPoint());
        }
        chess.setPoint(aim.getPoint());
        updateCheckStatus();
    }

    public List<Target> selectChess(Chess chess) {
        return chess.getLocation(this);
    }

    //Create and return a copy of the current Board object
    @Override
    public Board clone() throws CloneNotSupportedException {
        Board cloned = (Board) super.clone();
        cloned.pieceList = new ArrayList<>();
        for (Chess chess : pieceList) {
            Chess temp = chess.clone();
            cloned.pieceList.add(temp);
        }
        return cloned;
    }
}



