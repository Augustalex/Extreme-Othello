package views.createNewGame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import router.Router;
import views.FXMLViewController;
import views.ViewController;
import views.exceptions.ViewNotLoadedException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-09-29.
 */
public class NewGameViewController extends FXMLViewController implements Initializable{

    private static final String fxmlFileName = "NewGameView.fxml";

    @FXML
    ComboBox fuckIt;

    @FXML
    Button back;

    public NewGameViewController() {
        super(NewGameViewController.fxmlFileName);
    }

    @Override
    public void loadViewInto(Pane container) {
        this.loadFXLMInto(this.getClass(), this, container);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.back.setOnAction((e)-> Router.getApplicationRouter().previous());
    }
}
