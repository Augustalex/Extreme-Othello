package boardGameLibrary.boardGame.pawn;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by August on 2016-10-15.
 */
public class PawnDisplayModel {

    private Shape shape = new Rectangle();
    private Paint paint;

    public PawnDisplayModel(String imageResourceURL){
        this.paint = new ImagePattern(new Image(imageResourceURL));
    }

    public PawnDisplayModel(Color color){
        this.paint = color;
    }

    public PawnDisplayModel(Shape shape, Color color){
        this.paint = color;
        this.shape = shape;
    }

    public Paint getPaint(){
        return this.paint;
    }

    public Shape getShape(){
        return this.shape;
    }
}
