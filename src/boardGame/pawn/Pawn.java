package boardGame.pawn;

import boardGame.events.CellClickEvent;
import boardGame.player.Player;
import events.EventMediator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Pawn {
    protected Player owner;

    public Pawn(Player owner){
        this.owner = owner;
    }

    public Player getOwner(){
        return this.owner;
    }

    public void setOwner(Player newOwner){
        this.owner = newOwner;
    }
}
