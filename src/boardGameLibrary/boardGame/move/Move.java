package boardGameLibrary.boardGame.move;

/**
 * Created by August on 2016-09-30.
 */
public class Move {

    private PlayerAction[] actions;

    public Move(PlayerAction[] actions){
        this.actions = actions;
    }

    public PlayerAction[] getMove(){
        return this.actions;
    }
}
