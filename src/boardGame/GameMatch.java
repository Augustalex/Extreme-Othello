package boardGame;

import boardGame.player.Player;

/**
 * Created by August on 2016-09-30.
 */
public abstract class GameMatch {

    protected Player[] players;
    protected BoardMoveMaker board;

    public GameMatch(BoardMoveMaker board, Player[] players){
        this.board = board;
        this.players = players;
    }

    protected abstract void round();

    protected abstract void turn(Player player, BoardMoveMaker board);
}
