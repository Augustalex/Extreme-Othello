package boardGameLibrary.player;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.CellClickEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-10-02.
 */
public class VoidPlayer extends Player{

    public VoidPlayer() {
        super("Void", Color.TRANSPARENT);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, ObjectProperty<CellClickEvent> cellClickProperty) {

    }
}
