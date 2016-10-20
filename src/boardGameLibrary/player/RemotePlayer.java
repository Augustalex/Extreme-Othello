package boardGameLibrary.player;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.CellClickEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class RemotePlayer extends Player {

    public RemotePlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, ObjectProperty<CellClickEvent> cellClickProperty) {

    }
}
