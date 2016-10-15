package boardGame.player;

import boardGame.Move;
import boardGame.PlayerAction;
import boardGame.events.CellClickEvent;
import boardGame.events.PlayerMadeMoveEvent;
import events.EventMediator;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by August on 2016-09-30.
 */
public class LocalPlayer extends Player {

    private EventMediator<CellClickEvent> eventMediator;
    private EventMediator<PlayerMadeMoveEvent> madeMove;

    public LocalPlayer(EventMediator<CellClickEvent> cellClickObserver,){
        this.eventMediator = eventMediator;
    }

    @Override
    public void makeMove() {
        this.eventMediator.hasEventProperty().addListener(e ->{

            this.callback.getMapArguments().put("Move", move);

            try {
                this.callback.invoke();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }
}
