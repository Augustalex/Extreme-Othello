package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.scene.shape.Shape;

/**
 * Created by August on 2016-10-12.
 */
public abstract class ShapeMarker implements CellMarker {

    private Shape shape;

    public ShapeMarker(Shape shape){
        this.shape = shape;
    }

    @Override
    public Shape getShape(){
        return this.shape;
    }


}
