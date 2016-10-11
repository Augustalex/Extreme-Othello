package boardGame;

import boardGame.player.Player;
import boardGame.player.VoidPlayer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.awt.*;

/**
 * Created by August on 2016-09-30.
 */
public abstract class GameBoard {

    protected Pawn[][] board;
    private int width;
    private int height;

    public GameBoard(int width, int height){
        this.board = new Pawn[width][height];
        this.width = width;
        this.height = height;

        for(int y = 0; y < this.height; y++)
            for(int x = 0; x < this.width; x++)
                this.board[x][y] = new VoidPawn();
    }

    public abstract boolean isLegalMove(Player player, Move move);

    public abstract Move getAvailableMoves();

    public abstract void makeMove(Player player, Move move);

    public boolean withinBounds(Point position){
        if(position.x < 0 || position.y > this.width-1)
            return false;
        else if(position.y < 0 || position.y > this.height-1)
            return false;
        else
            return true;
    }

    public Pawn getPawn(Point position){
        return this.board[position.x][position.y];
    }

    public void setPawn(Point position, Pawn newPawn){
        this.board[position.x][position.y] = newPawn;
    }

    public boolean isEmpty(Point position){
        return this.board[position.x][position.y].getOwner() instanceof VoidPlayer;
    }

    public abstract void setStartPawns(Player[] players);
}
