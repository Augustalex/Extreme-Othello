package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

/**
 * Created by August on 2016-10-12.
 */
public class CircleMarker extends ShapeMarker {

    private DoubleProperty width;

    public CircleMarker(Circle circle) {
        super(circle);

        this.width = new SimpleDoubleProperty(circle.getRadius()*2);
        circle.radiusProperty().bind(this.width.divide(2));
    }

    public DoubleProperty getWidthProperty(){
        return this.width;
    }

    public DoubleProperty getHeightProperty(){
        return this.width;
    }
}
