package boardGame;

import boardGame.player.VoidPlayer;

/**
 * Created by August on 2016-09-30.
 */
public class VoidPawn extends Pawn{

    public VoidPawn() {
        super(new VoidPlayer());
    }
}
