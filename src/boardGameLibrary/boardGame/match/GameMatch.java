package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.players.Player;
import boardGamePlugins.othello.board.OthelloBoard;
import boardGamePlugins.othello.board.OthelloBoardMoveMaker;
import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;

/**
 * Created by August on 2016-10-19.
 */
public interface GameMatch {

    static GameMatch createGameMatch(String gameType, Player[] players, boolean isOnline) {

        BoardMoveMaker moveMaker;
        switch (gameType.toLowerCase()) {
            case "othello":
                moveMaker = new OthelloBoardMoveMaker(new OthelloBoard());
                moveMaker.setStartPawns(players);
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (isOnline)
            return new OnlineGameMatch();
        else
            return new LocalGameMatch(moveMaker, players);
    }

    void run();

    void turn(Player player);

    BoardMoveMaker getBoardMoveMaker();

    MoveProperties getMoveProperties();

}
