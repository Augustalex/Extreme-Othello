package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.player.Player;
import javafx.beans.property.ObjectProperty;

/**
 * Created by August on 2016-10-19.
 */
public class OnlineGameMatch implements GameMatch {

    @Override
    public void run() {
    }

    @Override
    public void turn(Player player) {

    }

    @Override
    public BoardMoveMaker getBoardMoveMaker() {
        return null;
    }

    @Override
    public ObjectProperty<CellClickEvent> cellClickProperty() {
        return null;
    }
}
