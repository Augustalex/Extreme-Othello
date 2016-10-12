package views.gameView;

import boardGame.GameMatch;
import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.FXMLViewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

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
        gameBoardContainer.getChildren().add(GameBoardFactory.createBoard(8));

        //gameBoardContainer.setStyle("-fx-border-color: black; -fx-background-color: yellow");
        //gameBoardContainer.minWidthProperty().bind(this.container.widthProperty());
    }
}
