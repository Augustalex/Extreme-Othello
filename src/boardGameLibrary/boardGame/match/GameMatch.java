package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.players.Player;
import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;

/**
 * Created by August on 2016-10-19.
 */
public interface GameMatch {

    void run();

    void turn(Player player);

    BoardMoveMaker getBoardMoveMaker();

    ObjectProperty<CellClickEvent> cellClickProperty();

    ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty();
}
