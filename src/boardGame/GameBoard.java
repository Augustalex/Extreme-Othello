package boardGame;

import boardGame.pawn.Pawn;
import boardGame.player.VoidPlayer;

import java.awt.*;

/**
 * Created by August on 2016-10-13.
 */
public class GameBoard{

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

    public boolean withinBounds(Point position){
        if(position.x < 0 || position.y > this.width-1)
            return false;
        else if(position.y < 0 || position.y > this.height-1)
            return false;
        else
            return true;
    }

    public Dimension getBoundaries(){
        return new Dimension(this.width, this.height);
    }

    public Pawn getPawn(Point position){
        return this.board[position.x][position.y];
    }

    protected void setPawn(Point position, Pawn newPawn){
        this.board[position.x][position.y] = newPawn;
    }

    public boolean isEmpty(Point position){
        return this.board[position.x][position.y].getOwner() instanceof VoidPlayer;
    }
}
