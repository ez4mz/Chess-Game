package XXLChess;

import processing.core.PApplet;

public class Player {
    public int seconds;
    private int increment;
    private int counter = 0;
    private final boolean is_white;
    private boolean isTurn;
    private Game game;

    public enum PlayerType {
        Human, CPU
    }

    protected PlayerType playerType;

    public Player(int seconds, int increment, boolean is_white, PlayerType playerType) {
        this.seconds = seconds;
        this.increment = increment;
        this.is_white = is_white;
        this.playerType = playerType;
    }

    public boolean isWhite() {
        return is_white;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getIncrement() {
        return increment;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void addTime() {
        this.seconds += this.increment;
    }

    public String getFormatTime() {
        int minutes = seconds / 60;
        int remainderSeconds = seconds % 60;
        if (seconds < 0) {
            seconds = 0;
        }
        if (isTurn) {
            String formattedTime = String.format("%2d:%02d", minutes, remainderSeconds);
            counter++;
            if (counter == 60) {
                    seconds--;
                    counter = 0;
            }
            return formattedTime;
        } else {
            return String.format("%2d:%02d", minutes, remainderSeconds);
        }
    }


    public void draw(PApplet app) {
        String time = getFormatTime();
        app.textSize(25);
        app.fill(255);

        if (playerType == PlayerType.Human) {
            app.text(time, 700, 62);
            if (this.getSeconds() == 0) {
                app.textSize(13);
                app.fill(255);
                app.text("You won by time", 680, 400);
            }
        } else {
            app.text(time, 700, 610);
            if (this.getSeconds() == 0) {
                app.textSize(13);
                app.fill(255);
                app.text("You lost by time", 680, 400);
            }
        }
    }

}
