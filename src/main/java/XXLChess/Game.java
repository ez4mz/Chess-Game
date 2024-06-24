package XXLChess;

import XXLChess.chess.*;
import XXLChess.util.Color;
import XXLChess.util.Point;
import XXLChess.util.Target;
import XXLChess.util.Sound;
import processing.data.JSONObject;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Game {
    private Board board;
    private Player human;
    private Player cpu;
    private Point previous_location;
    private Point current_location;
    private Chess selectedChess;
    private List<Target> targets;
    private Player current;
    private Chess moving;
    public boolean gameEnded;

    public Game(JSONObject levelConfiguration, PApplet app){
        initialize(levelConfiguration, app);
    }

    /*
    Read the layout file and initialize the chess board based on the contents of the file.
    Each line in the layout file corresponds to a row on the chess board.
    Each character in a line represents a position on the chess board.
    The content of each character indicates the type of chess piece at this position.
     */

    private void initialize(JSONObject config, PApplet app) {
        this.board = new Board();
        String layout = config.getString("layout");
        try (BufferedReader br = new BufferedReader(new FileReader(layout))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    int width = line.length();
                    for(int j = 0; j < width; j ++) {
                        addToBoard(line.charAt(j),j, lineNumber,app);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject timeControls = config.getJSONObject("time_controls");
        JSONObject player = timeControls.getJSONObject("player");
        int playerSeconds = player.getInt("seconds");
        int playerIncrement = player.getInt("increment");
        JSONObject cpu = timeControls.getJSONObject("cpu");
        int cpuSeconds = cpu.getInt("seconds");
        int cpuIncrement = cpu.getInt("increment");
        String playerColour = config.getString("player_colour");
        boolean is_white = playerColour.equals("white");
        this.human = new Player(playerSeconds, playerIncrement, is_white, Player.PlayerType.Human);
        this.cpu = new Player(cpuSeconds, cpuIncrement, !is_white, Player.PlayerType.CPU);
        double piece_movement_speed = config.getDouble("piece_movement_speed");
        double max_movement_time = config.getDouble("max_movement_time");

        if(is_white){
            current = this.human;
        } else{
            current = this.cpu;
        }

    }

    //Add a specific piece to the game board, based on the type of piece and its starting position (x, y).
    private void addToBoard(char type, int x, int y, PApplet app){
        PImage  b_amazonImg, b_archbishopImg, b_bishop, b_camelImg, b_chancellorImg, b_kingImg, b_knightImg,
                b_guardImg, b_pawnImg, b_queenImg, b_rookImg, w_amazonImg, w_archbishopImg, w_bishop, w_camelImg,
                w_chancellorImg, w_kingImg, w_knightImg, w_guardImg, w_pawnImg, w_queenImg, w_rookImg;


        b_amazonImg = app.loadImage("src/main/resources/XXLChess/b-amazon.png");
        b_amazonImg.resize(48,48);
        b_archbishopImg = app.loadImage("src/main/resources/XXLChess/b-archbishop.png");
        b_archbishopImg.resize(48,48);
        b_bishop = app.loadImage("src/main/resources/XXLChess/b-bishop.png");
        b_bishop.resize(48,48);
        b_camelImg = app.loadImage("src/main/resources/XXLChess/b-camel.png");
        b_camelImg.resize(48,48);
        b_chancellorImg = app.loadImage("src/main/resources/XXLChess/b-chancellor.png");
        b_chancellorImg.resize(48,48);
        b_kingImg = app.loadImage("src/main/resources/XXLChess/b-king.png");
        b_kingImg.resize(48,48);
        b_knightImg = app.loadImage("src/main/resources/XXLChess/b-knight.png");
        b_knightImg.resize(48,48);
        b_guardImg = app.loadImage("src/main/resources/XXLChess/b-knight-king.png");
        b_guardImg.resize(48,48);
        b_pawnImg = app.loadImage("src/main/resources/XXLChess/b-pawn.png");
        b_pawnImg.resize(48,48);
        b_queenImg = app.loadImage("src/main/resources/XXLChess/b-queen.png");
        b_queenImg.resize(48,48);
        b_rookImg = app.loadImage("src/main/resources/XXLChess/b-rook.png");
        b_rookImg.resize(48,48);
        w_amazonImg = app.loadImage("src/main/resources/XXLChess/w-amazon.png");
        w_amazonImg.resize(48,48);
        w_archbishopImg = app.loadImage("src/main/resources/XXLChess/w-archbishop.png");
        w_archbishopImg.resize(48,48);
        w_bishop = app.loadImage("src/main/resources/XXLChess/w-bishop.png");
        w_bishop.resize(48,48);
        w_camelImg = app.loadImage("src/main/resources/XXLChess/w-camel.png");
        w_camelImg.resize(48,48);
        w_chancellorImg = app.loadImage("src/main/resources/XXLChess/w-chancellor.png");
        w_chancellorImg.resize(48,48);
        w_kingImg = app.loadImage("src/main/resources/XXLChess/w-king.png");
        w_kingImg.resize(48,48);
        w_knightImg = app.loadImage("src/main/resources/XXLChess/w-knight.png");
        w_knightImg.resize(48,48);
        w_guardImg = app.loadImage("src/main/resources/XXLChess/w-knight-king.png");
        w_guardImg.resize(48,48);
        w_pawnImg = app.loadImage("src/main/resources/XXLChess/w-pawn.png");
        w_pawnImg.resize(48,48);
        w_queenImg = app.loadImage("src/main/resources/XXLChess/w-queen.png");
        w_queenImg.resize(48,48);
        w_rookImg = app.loadImage("src/main/resources/XXLChess/w-rook.png");
        w_rookImg.resize(48,48);
        Chess piece = null;
        switch (type) {
            case 'A':
                piece = new Amazon(x,y,false, b_amazonImg);
               break;
            case 'a':
                piece = new Amazon(x,y,true, w_amazonImg);
                break;
            case 'H':
                piece = new Archbishop(x,y,false, b_archbishopImg);
                break;
            case 'h':
                piece = new Archbishop(x,y,true, w_archbishopImg);
                break;
            case 'B':
                piece = new Bishop(x,y,false, b_bishop);
                break;
            case 'b':
                piece = new Bishop(x,y,true, w_bishop);
                break;
            case 'C':
                piece = new Camel(x,y,false, b_camelImg);
                break;
            case 'c':
                piece = new Camel(x,y,true, w_camelImg);
                break;
            case 'E':
                piece = new Chancellor(x,y,false, b_chancellorImg);
                break;
            case 'e':
                piece = new Chancellor(x,y,true, w_chancellorImg);
                break;
            case 'K':
                piece = new King(x,y,false, b_kingImg);
                piece.setKing(true);
                break;
            case 'k':
                piece = new King(x,y,true, w_kingImg);
                piece.setKing(true);
                break;
            case 'N':
                piece = new Knight(x,y,false, b_knightImg);
                break;
            case 'n':
                piece = new Knight(x,y,true, w_knightImg);
                break;
            case 'G':
                piece = new Guard(x,y,false, b_guardImg);
                break;
            case 'g':
                piece = new Guard(x,y,true, w_guardImg);
                break;
            case 'P':
                piece = new Pawn(x,y,false, b_pawnImg);
                break;
            case 'p':
                piece = new Pawn(x,y,true, w_pawnImg);
                break;
            case 'Q':
                piece = new Queen(x,y,false, b_queenImg);
                break;
            case 'q':
                piece = new Queen(x,y,true, w_queenImg);
                break;
            case 'R':
                piece = new Rook(x,y,false, b_rookImg);
                break;
            case 'r':
                piece = new Rook(x,y,true, w_rookImg);
        }
        if(piece != null) {
            this.board.addChess(piece);
        }
    }

    public Board getBoard(){
        return this.board;
    }

    //Handle the logic for the computer player's move.
    public void aiMove() {

        if (current.getSeconds() == 0 || gameEnded || board.checkMate(current.isWhite())) {
            endGame();
            return;
        }

        List<Chess> chessList = board.getPieceList();
        Chess bestMoveChess = null;
        Target bestMoveAim = null;
        int maxScore = Integer.MIN_VALUE;

        for(Chess chess : chessList) {
            if(chess.isWhite() == cpu.isWhite()) {
                List<Target> ls = chess.getLocation(board);
                for (Target aim : ls) {
                    int score = 0;
                    Chess target = board.findChess(aim.getPoint().getX(), aim.getPoint().getY());
                    if (target != null) {
                        score = (int) target.getValue();
                    }
                    if (score > maxScore) {
                        maxScore = score;
                        bestMoveChess = chess;
                        bestMoveAim = aim;
                    }
                }
            }
        }
        // If there is no valuable piece to capture, select a random move
        if (bestMoveChess == null) {
            Random rand = new Random();
            Chess randomChess;
            do {
                randomChess = chessList.get(rand.nextInt(chessList.size()));
            } while (randomChess.isWhite() != cpu.isWhite() || randomChess.getLocation(board).isEmpty());

            List<Target> aims = randomChess.getLocation(board);
            bestMoveAim = aims.get(rand.nextInt(aims.size()));
            bestMoveChess = randomChess;
        }

        previous_location = bestMoveChess.getPoint();
        current_location = bestMoveAim.getPoint();
        board.move(bestMoveChess, bestMoveAim);
        moving = bestMoveChess;
        cpu.addTime();

        Sound.playSound("src/main/resources/move-self.wav");
    }


    //Handle the logic of a player's move in a game, implements the process of a player selecting and moving a piece.
    public void humanMove(int x, int y) {

        if (current.getSeconds() == 0 || gameEnded || board.checkMate(current.isWhite()) ) {
            return;
        }
        //Convert pixel coordinates into chess board coordinates.
        x = x/48;
        y = y/48;
        Chess chess = board.findChess(x,y);
        //If no piece has been selected yet.
        if(selectedChess == null) {
            //if the located piece is of the current player's color.
            if(chess != null && chess.isWhite() == current.isWhite()) {
                //If so, it selects this piece and computes all possible move locations.
                selectedChess = chess;
                targets = chess.getLocation(board);
            }
        } else{//If a piece has already been selected
            //If the player clicked on a different piece
            if(chess != selectedChess){
                //Goes through all possible move locations for the previously selected piece
                for(Target aim: targets) {
                    //Check if the clicked location is one of them
                    if(aim.getPoint().getX() == x && aim.getPoint().getY() == y) {
                        //If yes, move the selected piece to that location
                        previous_location = selectedChess.getPoint();
                        current_location = aim.getPoint();
                        board.move(selectedChess, aim);
                        moving = selectedChess;
                        human.addTime();
                        break;
                    }
                }
            }
            //If the player clicked on the same piece again, the piece gets deselected.
            selectedChess = null;
        }
        Sound.playSound("src/main/resources/move-self.wav");
    }

    public void endGame() {
        this.gameEnded = true;
    }

    public void draw(PApplet app) {
        app.background(Color.GRAY);
        app.rect(0,0,672,792);
        board.drawBoard(app);
        int squareSize = 48;
        //If the previous position is not empty (that is, there is a chess piece moving)
        if(previous_location != null) {
            //Fill the alternative green color according to the coordinates of the board
            app.fill((previous_location.getX() + previous_location.getY())%2 == 0 ? Color.LIGHT_GREEN: Color.DARK_GREEN);
            app.rect(previous_location.getX() * squareSize, previous_location.getY()* squareSize, squareSize, squareSize);
            app.fill((current_location.getX() + current_location.getY())%2 == 0 ? Color.LIGHT_GREEN: Color.DARK_GREEN);
            app.rect(current_location.getX() * squareSize, current_location.getY()* squareSize, squareSize, squareSize);
        }
        //If a piece is selected
        if(selectedChess != null) {
            for(Target aim: targets) {
                int x = aim.getPoint().getX();
                int y = aim.getPoint().getY();
                //Fill the alternating color depending on whether the opponent's piece can be captured
                if(!aim.isCapture()) {
                    app.fill((x+y)%2 == 0 ? Color.LIGHT_BLUE: Color.DARK_BLUE);
                } else {
                    app.fill(Color.LIGHT_RED);
                }
                app.rect(x * squareSize, y* squareSize, squareSize, squareSize);
            }
            //Fill SELECT_GREEN into the tile of selected piece
            app.fill(Color.SELECT_GREEN);
            app.rect(selectedChess.getX() * squareSize, selectedChess.getY()* squareSize, squareSize, squareSize);

        } else if(moving != null){
            if(!moving.tick(6,1,60)){
                moving = null;
                if(current == cpu){
                    current = human;
                    cpu.setTurn(true);
                    human.setTurn(false);
                } else{
                    current = cpu;
                    human.setTurn(true);
                    cpu.setTurn(false);
                    aiMove();
                }
            }
        }

        human.draw(app);
        cpu.draw(app);

        Point whiteKing = board.getKingLocation(true);
        Point blackKing = board.getKingLocation(false);

        //Pieces contributing to checkmate have their tiles highlighted in red
        if (whiteKing != null && board.checkMate(true)) {
            app.textSize(15);
            app.fill(255);
            app.text("You lost", 680, 200);
            app.text("by checkmate", 680, 220);
            app.text("Press 'r' to", 680, 300);
            app.text("restart the game", 680, 320);
            for (Chess piece : board.allCheckingPieces(true)) {
                app.fill(Color.LIGHT_RED);
                app.rect(piece.getX() * squareSize, piece.getY() * squareSize, squareSize, squareSize);
            }
            app.fill(Color.DARK_RED);
            app.rect(whiteKing.getX() * squareSize, whiteKing.getY() * squareSize, squareSize, squareSize);
        } else if (whiteKing != null && board.check(true)) {
            app.textSize(13);
            app.fill(255);
            app.text("Checked!", 680, 200);
            app.fill(Color.DARK_RED);
            app.rect(whiteKing.getX() * squareSize, whiteKing.getY() * squareSize, squareSize, squareSize);
        }
        //Pieces contributing to checkmate have their tiles highlighted in red
        if (blackKing != null && board.checkMate(false)) {
            app.textSize(13);
            app.fill(255);
            app.text("You won", 680, 200);
            app.text("by checkmate", 680, 220);
            app.text("Press 'r' to", 680, 300);
            app.text("restart the game", 680, 320);
            for (Chess piece : board.allCheckingPieces(false)) {
                app.fill(Color.LIGHT_RED);
                app.rect(piece.getX() * squareSize, piece.getY() * squareSize, squareSize, squareSize);
            }
            app.fill(Color.DARK_RED);
            app.rect(blackKing.getX() * squareSize, blackKing.getY() * squareSize, squareSize, squareSize);
        } else if (blackKing != null && board.check(false)) {
            app.textSize(15);
            app.fill(255);
            app.text("Checked!", 680, 200);
            app.fill(Color.DARK_RED);
            app.rect(blackKing.getX() * squareSize, blackKing.getY() * squareSize, squareSize, squareSize);
        }

        board.drawChess(app);
    }
}