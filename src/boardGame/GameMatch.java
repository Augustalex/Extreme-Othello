package boardGame;

import boardGame.events.CellClickEvent;
import boardGame.player.Player;
import events.EventMediator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by August on 2016-09-30.
 */
public abstract class GameMatch {

    protected Player[] players;
    protected BoardMoveMaker board;

    private EventMediator<CellClickEvent> eventMediator = new EventMediator<>();

    public GameMatch(BoardMoveMaker board, Player[] players){
        this.board = board;
        this.players = players;
    }

    protected abstract void round();

    protected abstract void turn(Player player, BoardMoveMaker board);

    public BoardMoveMaker getBoardMoveMaker(){
        return this.board;
    }

    public EventMediator<CellClickEvent> getEventMediator(){
        return this.eventMediator;
    }


}
