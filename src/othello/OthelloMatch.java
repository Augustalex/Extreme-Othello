package othello;

import boardGame.GameBoard;
import boardGame.GameMatch;
import boardGame.Move;
import boardGame.player.Player;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloMatch extends GameMatch {

    public OthelloMatch(OthelloBoard board, Player[] players) {
        super(board, players);

        this.board.setStartPawns(players);
    }

    @Override
    protected void round() {
        for(Player player : this.players){
            turn(player, this.board);
        }
    }

    @Override
    protected void turn(Player player, GameBoard board) {
        Move move;
        do {
            move = player.getMove();
        } while (!board.isLegalMove(player, move));
        board.makeMove(player, move);
    }
}
