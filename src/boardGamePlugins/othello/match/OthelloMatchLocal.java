package boardGamePlugins.othello.match;

import boardGameLibrary.boardGame.match.LocalGameMatch;
import boardGameLibrary.player.Player;
import boardGamePlugins.othello.board.OthelloBoardMoveMaker;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloMatchLocal extends LocalGameMatch {

    public OthelloMatchLocal(OthelloBoardMoveMaker board, Player[] players) {
        super(board, players);

        this.board.setStartPawns(players);
    }

}
