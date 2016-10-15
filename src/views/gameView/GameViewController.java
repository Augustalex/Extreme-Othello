package views.gameView;

import boardGame.GameMatch;
import boardGame.events.CellClickEvent;
import events.EventMediator;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import views.FXMLViewController;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Created by August on 2016-09-29.
 */
public class GameViewController extends FXMLViewController implements Initializable{
    
    private static final String fxmlFileName = "GameView.fxml";

    private GameMatch match;

    @FXML
    private HBox gameBoardContainer;
    
    public GameViewController(GameMatch match){
        super(GameViewController.fxmlFileName);

        this.match = match;
    }

    @Override
    public void loadViewInto(Pane container) {
        this.loadFXLMInto(this.getClass(), this, container);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Game board boundaries
        Dimension boundaries = this.match.getBoardMoveMaker().getBoundaries();

        //Creating game board
        Pane gameBoard = GameBoardFactory.createBoard(gameBoardContainer, boundaries.width);
        gameBoardContainer.getChildren().add(gameBoard);

        //Fixing game board dimension properties
        gameBoardContainer.widthProperty().addListener((e) -> squareBindTo(gameBoard, gameBoardContainer));
        gameBoardContainer.heightProperty().addListener((e) -> squareBindTo(gameBoard, gameBoardContainer));

        squareBindTo(gameBoard, gameBoardContainer);
        
        System.out.println(this.container.heightProperty().get());
        System.out.println(gameBoardContainer.heightProperty().get());


        // Doing game logic here, hellooo, gosh.
        listenToGameBoardView(gameBoard, boundaries, match.getEventMediator());
    }
    
    private void squareBindTo(Region binder, Region container){
        if(container.widthProperty().get() > container.heightProperty().get()){
            bindTwoToOneDimensions(binder, container.heightProperty());
        }
        else{
            bindTwoToOneDimensions(binder, container.widthProperty());
        }
    }
    
    private void bindTwoToOneDimensions(Region binder, ReadOnlyDoubleProperty bindTo){
        bindOneToOneDimension(binder.minWidthProperty(), binder.maxWidthProperty(), bindTo);
        bindOneToOneDimension(binder.minHeightProperty(), binder.maxHeightProperty(), bindTo);
    }
    
    private void bindOneToOneDimension(DoubleProperty minDimension, DoubleProperty maxDimension, ReadOnlyDoubleProperty bindTo){
        minDimension.unbind();
        minDimension.bind(bindTo);

        maxDimension.unbind();
        maxDimension.bind(bindTo);
    }

    private void listenToGameBoardView(Pane boardView, Dimension boundaries, EventMediator<CellClickEvent> eventMediator){
        Node[] viewCells = (Node[]) boardView.getChildren().toArray();

        for(int y = 0; y < boundaries.height; y++){
            for(int x = 0; x < boundaries.width; x++){
                final Point position = new Point(x, y);
                viewCells[(y*boundaries.width) + x].setOnMouseClicked(e -> eventMediator.setEvent(new CellClickEvent(position)));
            }
        }
    }
}

