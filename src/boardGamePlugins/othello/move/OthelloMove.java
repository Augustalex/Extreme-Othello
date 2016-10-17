package boardGamePlugins.othello.move;

import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.boardGame.move.PlayerAction;

/**
 * Created by August on 2016-09-30.
 */
public class OthelloMove extends Move {

    public OthelloMove(PlayerAction action) {
        super(new PlayerAction[]{action});
    }
}
