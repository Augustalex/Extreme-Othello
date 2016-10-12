package views.gameView;

import boardGame.GameMatch;
import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.FXMLViewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Created by August on 2016-09-29.
 */
public class GameViewController extends FXMLViewController implements Initializable{
    
    private static final String fxmlFileName = "GameView.fxml";
    
    @FXML
    HBox gameBoardContainer;
    
    public GameViewController(GameMatch match){
        super(GameViewController.fxmlFileName);
    }

    @Override
    public void loadViewInto(Pane container) {
        this.loadFXLMInto(this.getClass(), this, container);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane gameBoard = GameBoardFactory.createBoard(gameBoardContainer, 8);
        gameBoardContainer.getChildren().add(gameBoard);

        //gameBoardContainer.setStyle("-fx-border-color: black; -fx-background-color: yellow");
        
        this.container.widthProperty().addListener((e) -> squareBindTo(gameBoard, gameBoardContainer));
        this.container.heightProperty().addListener((e) -> squareBindTo(gameBoard, gameBoardContainer));
        
        gameBoardContainer.heightProperty().addListener((e) -> System.out.println(gameBoardContainer.heightProperty().get()));
        
        squareBindTo(gameBoard, gameBoardContainer);
        
        System.out.println(this.container.heightProperty().get());
        System.out.println(gameBoardContainer.heightProperty().get());
        
    }
    
    public void squareBindTo(Region binder, Region container){
        if(container.widthProperty().get() > container.heightProperty().get()){
            bindTwoDimensions(binder, container.heightProperty());
        }
        else{
            bindTwoDimensions(binder, container.widthProperty());
        }
    }
    
    public void bindTwoDimensions(Region binder, ReadOnlyDoubleProperty bindTo){
        bindOneDimension(binder.minWidthProperty(), binder.maxWidthProperty(), bindTo);
        bindOneDimension(binder.minHeightProperty(), binder.maxHeightProperty(), bindTo); 
    }
    
    public void bindOneDimension(DoubleProperty minDimension, DoubleProperty maxDimension, ReadOnlyDoubleProperty bindTo){
        minDimension.bind(bindTo);
        maxDimension.bind(bindTo);
    }
}

