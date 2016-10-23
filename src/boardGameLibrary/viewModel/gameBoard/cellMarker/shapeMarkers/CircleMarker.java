package boardGameLibrary.viewModel.gameBoard.cellMarker.shapeMarkers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

/**
 * Used to create a circle formed CellMarker.
 */
public class CircleMarker extends ShapeMarker{

    private DoubleProperty radius;

    /**
     * Requires a circle to instantiate.
     * @param circle a new CircleMarker (CellMarker).
     */
    public CircleMarker(Circle circle) {
        super(circle);

        this.radius = new SimpleDoubleProperty(circle.getRadius()*2);
        circle.radiusProperty().bind(this.radius.divide(2));
    }

    /**
     *
     * @return a width property represented by the circle radius.
     */
    public DoubleProperty getWidthProperty(){
        return this.radius;
    }

    /**
     *
     * @return a height property represented by the circle radius.
     */
    public DoubleProperty getHeightProperty(){
        return this.radius;
    }

}
