package boardGameLibrary.boardGame.board;

import boardGameLibrary.eventWrappers.CellChangeEvent;
import boardGameLibrary.boardGame.pawn.Pawn;
import boardGameLibrary.player.VoidPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;

/**
 * Created by August on 2016-10-13.
 */
public class GameBoard{

    protected Pawn[][] board;

    private ObservableList<CellChangeEvent> cellChangeObserver = FXCollections.observableArrayList();

    private int width;
    private int height;

    public GameBoard(int width, int height){
        this.board = new Pawn[width][height];
        this.width = width;
        this.height = height;
    }

    public boolean withinBounds(Point position){
        return (position.x >= 0 && position.x <= this.width-1)
            && (position.y >= 0 && position.y <= this.height-1);
    }

    public Dimension getBoundaries(){
        return new Dimension(this.width, this.height);
    }

    public Pawn getPawn(Point position){
        return this.board[position.x][position.y];
    }

    public void setPawn(Point position, Pawn newPawn){
        this.board[position.x][position.y] = newPawn;
        this.cellChangeObserver.add(new CellChangeEvent(position));
        this.cellChangeObserver.clear();
        System.out.println("Flipped: " + position);
    }

    public boolean isEmpty(Point position){
        return this.board[position.x][position.y].getOwner() instanceof VoidPlayer;
    }

    public ObservableList<CellChangeEvent> getCellChangeObserver(){
        return this.cellChangeObserver;
    }


}
