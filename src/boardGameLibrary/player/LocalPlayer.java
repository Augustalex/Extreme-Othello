package boardGameLibrary.player;
import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class LocalPlayer extends Player {

    public LocalPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, ObjectProperty<CellClickEvent> cellClickProperty) {
        ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty = new SimpleObjectProperty<>();

        ChangeListener<PlayerMadeMoveEvent> madeMoveListener = (observableValue, oldValue, newValue) -> {
            boardMoveMaker.makeMove(observableValue.getValue().getPlayer(), observableValue.getValue().getMove());
        };

        madeMoveProperty.addListener(e -> {
            madeMoveListener.changed(madeMoveProperty, null, madeMoveProperty.get());
            madeMoveProperty.removeListener(madeMoveListener);
        });

        new Thread(new PlayerMoveGetter(this, cellClickProperty, madeMoveProperty)).start();
    }

}
