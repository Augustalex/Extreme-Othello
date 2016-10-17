package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import boardGameLibrary.viewModel.gameBoard.cellMarker.exceptions.InvalidCellMarkerShapeException;

/**
 * Created by August on 2016-10-12.
 */
public interface CellMarker {

    static CellMarker createCellMarker(Shape shape){
        if(shape instanceof Rectangle)
            return new RectangleMarker((Rectangle)shape);
        else if(shape instanceof Circle)
            return new CircleMarker((Circle)shape);
        else
            throw new InvalidCellMarkerShapeException();
    }

    DoubleProperty getWidthProperty();

    DoubleProperty getHeightProperty();

    Shape getShape();

}
