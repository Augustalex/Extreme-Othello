package othello;

import boardGame.pawn.Pawn;
import boardGame.player.Player;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class OthelloPawn extends Pawn {
    private Color color;

    public OthelloPawn(Player owner, Color color){
        super(owner);
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
}
