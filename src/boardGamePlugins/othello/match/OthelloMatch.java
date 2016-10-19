package boardGamePlugins.othello.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import boardGameLibrary.player.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import boardGamePlugins.othello.board.OthelloBoardMoveMaker;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloMatch extends GameMatch {

    public OthelloMatch(OthelloBoardMoveMaker board, Player[] players) {
        super(board, players);

        this.board.setStartPawns(players);
    }

}
