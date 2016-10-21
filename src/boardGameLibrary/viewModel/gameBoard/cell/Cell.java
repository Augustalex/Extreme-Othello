/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardGameLibrary.viewModel.gameBoard.cell;

import javafx.scene.layout.StackPane;
import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;

/**
 *
 * @author S132063
 */
public class Cell extends StackPane {
    // Indicate the row and column of this cell in the board
    private static final double CELL_MARK_PADDING_FACTOR = 0.85;
    private static final double CELL_HIGHLIGHT_PADDING_FACTOR = 0.75;

    private StackPane markLayer;
    private StackPane highlightLayer;

    public Cell() {
        this.setId("gameBoardCell");

        this.markLayer = new StackPane();
        this.highlightLayer = new StackPane();

        this.getChildren().add(this.markLayer);
        this.getChildren().add(this.highlightLayer);
    }

    public void markCell(CellMarker marker){
        this.markLayer.getChildren().setAll(marker.getShape());

        marker.getWidthProperty().bind(this.widthProperty().multiply(CELL_MARK_PADDING_FACTOR));
        marker.getHeightProperty().bind(this.heightProperty().multiply(CELL_MARK_PADDING_FACTOR));
    }

    public void highlightCell(CellMarker marker){
        this.highlightLayer.getChildren().setAll(marker.getShape());

        marker.getWidthProperty().bind(this.widthProperty().multiply(CELL_HIGHLIGHT_PADDING_FACTOR));
        marker.getHeightProperty().bind(this.heightProperty().multiply(CELL_HIGHLIGHT_PADDING_FACTOR));
    }

    public void removeHighlight(){
        this.highlightLayer.getChildren().clear();
    }
}
