package boardGame;

import boardGame.pawn.Pawn;
import boardGame.player.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by August on 2016-09-30.
 */
public abstract class BoardMoveMaker extends GameBoard{

    private BooleanProperty madeMove = new SimpleBooleanProperty(false);

    public BoardMoveMaker(int width, int height){
        super(width, height);
    }

    public abstract boolean isLegalMove(Player player, Move move);

    public abstract Move[] getAvailableMoves();

    public abstract void makeMove(Player player, Move move);

    public abstract void setStartPawns(Player[] players);

    public BooleanProperty madeMoveProperty(){
        return this.madeMove;
    }

    public Pawn[][] getBoard(){
        return this.board;
    }

}
