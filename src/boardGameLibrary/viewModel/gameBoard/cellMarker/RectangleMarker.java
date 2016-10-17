package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 * Created by August on 2016-10-12.
 */
public class RectangleMarker extends ShapeMarker {

    private DoubleProperty width;
    private DoubleProperty height;

    public RectangleMarker(Rectangle rectangle){
        super(rectangle);

        this.width = rectangle.widthProperty();
        this.height = rectangle.heightProperty();
    }

    public DoubleProperty getWidthProperty(){
        return this.width;
    }

    public DoubleProperty getHeightProperty(){
        return this.height;
    }
}
