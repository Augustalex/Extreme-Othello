/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardGameLibrary.viewModel.gameBoard.cell;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;

/**
 *
 * @author S132063
 */
public class Cell extends StackPane {
    // Indicate the row and column of this cell in the board

    private static final double CELL_MARKER_PADDING_FACTOR = 0.85;
    public Cell() {
      this.setId("gameBoardCell");

    }

    private void handleMouseClick() {
        /**throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. */ 
        System.out.println("lalala");
        Circle circle = new Circle();
        circle.setFill(Color.BLACK);

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        markCell(CellMarker.createCellMarker(circle));

    }

    public void markCell(CellMarker marker){
        this.getChildren().setAll(marker.getShape());

        marker.getWidthProperty().bind(this.widthProperty().multiply(CELL_MARKER_PADDING_FACTOR));
        marker.getHeightProperty().bind(this.heightProperty().multiply(CELL_MARKER_PADDING_FACTOR));
    }
}
