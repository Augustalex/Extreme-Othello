package boardGame;

import boardGame.player.LocalConsolePlayer;
import boardGame.player.Player;
import boardGame.player.VoidPlayer;
import othello.OthelloBoard;
import othello.OthelloMatch;

/**
 * Created by August on 2016-09-30.
 */
public abstract class GameMatch {

    protected Player[] players;
    protected GameBoard board;

    public GameMatch(GameBoard board, Player[] players){
        this.board = board;
        this.players = players;
    }

    protected abstract void round();

    protected abstract void turn(Player player, GameBoard board);
}
