package views.gameView;

import boardGame.GameMatch;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import views.FXMLViewController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-09-29.
 */
public class GameViewController extends FXMLViewController implements Initializable{

    private static final String fxmlFileName = "GameView.fxml";

    public GameViewController(GameMatch match){
        super(GameViewController.fxmlFileName);
    }

    @Override
    public void loadViewInto(Pane container) {
        this.loadFXLMInto(this.getClass(), this, container);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
