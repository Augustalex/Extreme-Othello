package boardGame.player;

import boardGame.Move;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Player{

    protected Move[] availableMoves;

    public Player(Move[] availableMoves){
        this.availableMoves = availableMoves;
    }

    public abstract Move getMove();
}
