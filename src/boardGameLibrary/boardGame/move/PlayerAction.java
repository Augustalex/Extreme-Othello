package boardGameLibrary.boardGame.move;

import java.awt.*;

/**
 * Created by August on 2016-09-30.
 */
public class PlayerAction {
    private int x;
    private int y;

    public PlayerAction(Point position){
        this.x = position.x;
        this.y = position.y;
    }

    public PlayerAction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
