package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.eventWrappers.CellClickEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Player{

    protected String name;
    protected Color color;

    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }

    public abstract void makeMove(BoardMoveMaker boardMoveMaker, ObjectProperty<CellClickEvent> cellClickProperty, ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty);

    public Color getColor(){
        return this.color;
    }

    public String getName(){
        return this.name;
    }
}
