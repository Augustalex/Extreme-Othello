package boardGameLibrary.player;

import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.boardGame.move.PlayerAction;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by August on 2016-10-15.
 */
public class PlayerMoveGetter implements Runnable{

    private Player player;

    private ObjectProperty<PlayerMadeMoveEvent> madeMove;
    private ObjectProperty<CellClickEvent> cellClickEventObjectProperty;

    public PlayerMoveGetter(Player player, ObjectProperty<CellClickEvent> cellClickEventObjectProperty, ObjectProperty<PlayerMadeMoveEvent> madeMoveMediator){
        this.player = player;
        this.madeMove = madeMoveMediator;
        this.cellClickEventObjectProperty = cellClickEventObjectProperty;
    }

    @Override
    public void run() {
        this.cellClickedEventObjectProperty().addListener(new CellClickedPropertyEvent(this.player));
    }

    private class CellClickedPropertyEvent implements ChangeListener<CellClickEvent>{

        private Player player;

        public CellClickedPropertyEvent(Player player){
            this.player = player;
        }

        @Override
        public void changed(ObservableValue<? extends CellClickEvent> observable, CellClickEvent oldValue, CellClickEvent newValue) {
            PlayerAction action = new PlayerAction(observable.getValue().getPosition());
            Move move = new Move(new PlayerAction[]{action});

            System.out.println("INSIDE PlayerMoveGetter");
            madeMove.set(new PlayerMadeMoveEvent(this.player, move));

            observable.removeListener(this);
        }
    }

    public ObjectProperty<CellClickEvent> cellClickedEventObjectProperty(){
        return this.cellClickEventObjectProperty;
    }
}
