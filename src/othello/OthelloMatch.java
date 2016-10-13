package othello;

import boardGame.BoardMoveMaker;
import boardGame.GameMatch;
import boardGame.Move;
import boardGame.player.Player;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloMatch extends GameMatch {

    public OthelloMatch(OthelloBoardMoveMaker board, Player[] players) {
        super(board, players);

        this.board.setStartPawns(players);
    }

    @Override
    public void round() {
        for(Player player : this.players){
            turn(player, this.board);
        }
    }

    @Override
    public void turn(Player player, BoardMoveMaker board) {
        Move move;
        do {
            move = player.getMove();
        } while (!board.isLegalMove(player, move));
        board.makeMove(player, move);
    }
}
