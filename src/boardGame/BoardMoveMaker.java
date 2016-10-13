package boardGame;

import boardGame.player.Player;
import boardGame.player.VoidPlayer;

import java.awt.*;

/**
 * Created by August on 2016-09-30.
 */
public abstract class BoardMoveMaker extends GameBoard{

    public BoardMoveMaker(int width, int height){
        super(width, height);
    }

    public abstract boolean isLegalMove(Player player, Move move);

    public abstract Move[] getAvailableMoves();

    public abstract void makeMove(Player player, Move move);

    public abstract void setStartPawns(Player[] players);
}
