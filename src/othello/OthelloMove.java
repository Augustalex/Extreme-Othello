package othello;

import boardGame.Move;
import boardGame.PlayerAction;

/**
 * Created by August on 2016-09-30.
 */
public class OthelloMove extends Move {

    public OthelloMove(PlayerAction action) {
        super(new PlayerAction[]{action});
    }
}
