package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import hostDetails.Host;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Player implements Serializable{

    protected String name;
    private double red;
    private double green;
    private double blue;
    private double opacity;

    public Player(String name, Color color){
        this.name = name;

        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
    }

    public abstract void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties);

    public Color getColor(){
        return Color.color(this.red, this.green, this.blue, this.opacity);
    }

    public String getName(){
        return this.name;
    }

    public Player setRed(int red){
        this.red = red;
        return this;
    }

    public Player setGreen(int green){
        this.green = green;
        return this;
    }

    public Player setBlue(int blue){
        this.blue = blue;
        return this;
    }

    public Player setName(String name){
        this.name = name;
        return this;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    /**
     * Returns the connection details of the player host computer
     * If the player is local, then this is the local computers connection
     * details. If it is remote, it is that of the remote computer.
     */
    public abstract Host getConnectionDetails();
}
