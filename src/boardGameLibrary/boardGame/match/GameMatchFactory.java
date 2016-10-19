package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.player.Player;
import boardGamePlugins.othello.board.OthelloBoard;
import boardGamePlugins.othello.board.OthelloBoardMoveMaker;

/**
 * Created by jacobbergqvist on 2016-10-19.
 */
public class GameMatchFactory {
    public static GameMatch createGameMatch(String gameType, Player[] players, boolean isOnline) {

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
}
