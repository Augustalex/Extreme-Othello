package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.player.Player;
import javafx.beans.property.ObjectProperty;

/**
 * Created by August on 2016-10-19.
 */
public interface GameMatch {

    void run();

    void turn(Player player);

    BoardMoveMaker getBoardMoveMaker();

    ObjectProperty<CellClickEvent> cellClickProperty();
}
