package boardGame.player;

import boardGame.Move;
import boardGame.PlayerAction;
import boardGame.events.CellClickEvent;
import boardGame.events.PlayerMadeMoveEvent;
import events.EventMediator;

/**
 * Created by August on 2016-10-15.
 */
public class getPlayerMove implements Runnable{

    private EventMediator<PlayerMadeMoveEvent> madeMove;
    private EventMediator<CellClickEvent> cellClicked;

    public getPlayerMove(EventMediator<CellClickEvent> cellClickMediator, EventMediator<PlayerMadeMoveEvent> madeMoveMediator){
        this.madeMove = madeMoveMediator;
        this.cellClicked = cellClickMediator;
    }

    @Override
    public void run() {
        this.cellClicked.hasEventProperty().addListener(e -> {
            if(this.cellClicked.hasEventProperty().get()){
                CellClickEvent cellClick = this.cellClicked.getEvent();
                PlayerAction action = new PlayerAction(cellClick.getPosition());
                Move move = new Move(new PlayerAction[]{action});

                madeMove.setEvent(new PlayerMadeMoveEvent(move));
            }
        });
    }
}
