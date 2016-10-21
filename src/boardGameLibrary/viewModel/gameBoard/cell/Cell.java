/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardGameLibrary.viewModel.gameBoard.cell;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    public void markCell(CellMarker marker) {


        if (this.markLayer.getChildren().size() > 0) {
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            final KeyValue originKv = new KeyValue(this.markLayer.getChildren().get(0).scaleXProperty(), 1);
            final KeyValue kv = new KeyValue(this.markLayer.getChildren().get(0).scaleXProperty(), 0);
            final KeyFrame kf = new KeyFrame(Duration.millis(180), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            timeline.setOnFinished(e -> {

                final Timeline returnTimeline = new Timeline();
                returnTimeline.setCycleCount(1);
                returnTimeline.setAutoReverse(false);
                final KeyValue originKeyValue = new KeyValue(marker.getShape().scaleXProperty(), 0);
                final KeyValue destinationKeyValue = new KeyValue(marker.getShape().scaleXProperty(), 1);
                //final KeyFrame startFrame = new KeyFrame(Duration.millis(0), originKeyValue);
                final KeyFrame endFrame = new KeyFrame(Duration.millis(100), destinationKeyValue);
                //returnTimeline.getKeyFrames().add(startFrame);
                returnTimeline.getKeyFrames().add(endFrame);

                this.markLayer.getChildren().setAll(marker.getShape());
                marker.getWidthProperty().bind(this.widthProperty().multiply(CELL_MARK_PADDING_FACTOR));
                marker.getHeightProperty().bind(this.heightProperty().multiply(CELL_MARK_PADDING_FACTOR));
                marker.getShape().scaleXProperty().set(0);
                returnTimeline.play();


            });
        } else {
            this.markLayer.getChildren().setAll(marker.getShape());


            marker.getWidthProperty().bind(this.widthProperty().multiply(CELL_MARK_PADDING_FACTOR));
            marker.getHeightProperty().bind(this.heightProperty().multiply(CELL_MARK_PADDING_FACTOR));
        }
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
