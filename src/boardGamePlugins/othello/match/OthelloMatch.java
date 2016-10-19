package boardGamePlugins.othello.match;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.player.Player;
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
