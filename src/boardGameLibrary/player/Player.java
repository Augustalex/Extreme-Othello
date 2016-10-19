package boardGameLibrary.player;

import boardGameLibrary.boardGame.pawn.PawnDisplayModel;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

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

    public abstract void makeMove(ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty, ObjectProperty<CellClickEvent> cellClickProperty);

    public Color getColor(){
        return this.color;
    }

    public String getName(){
        return this.name;
    }
}
