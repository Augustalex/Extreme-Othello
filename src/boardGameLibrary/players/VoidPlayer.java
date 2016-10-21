package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.eventWrappers.CellClickEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by August on 2016-10-02.
 */
public class VoidPlayer extends Player{

    public VoidPlayer() {
        super("Void", Color.TRANSPARENT);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, ObjectProperty<CellClickEvent> cellClickProperty, ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty) {

    }
}
