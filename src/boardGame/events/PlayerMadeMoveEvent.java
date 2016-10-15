package boardGame.events;

import boardGame.Move;

/**
 * Created by August on 2016-10-15.
 */
public class PlayerMadeMoveEvent {

    private Move move;

    public PlayerMadeMoveEvent(Move move){
        this.move = move;
    }

    public Move getMove(){
        return this.move;
    }
}
