package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Player{

    protected String name;
    protected Color color;

    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }

    public abstract void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties);

    public Color getColor(){
        return this.color;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
