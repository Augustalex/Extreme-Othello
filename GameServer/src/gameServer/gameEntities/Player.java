package gameServer.gameEntities;

import javafx.scene.paint.Color;

/**
 * Created by August on 2017-01-02.
 */
public class Player {

    public String name;
    public double red;
    public double green;
    public double blue;
    public double opacity;

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

    public Color getColor(){
        return Color.rgb((int)red, (int)green, (int)blue, opacity);
    }
}
