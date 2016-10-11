package views.mainMenu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import router.Router;
import views.FXMLViewController;
import views.ViewController;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-09-29.
 */
public class MainViewController extends FXMLViewController implements Initializable{

    private static final String fxmlFileName = "MainView.fxml";

    @FXML
    Label mainTitle;

    @FXML
    Button newGameButton;

    public MainViewController(){
        super(MainViewController.fxmlFileName);
    }

    @Override
    public void loadViewInto(Pane container) {
        this.loadFXLMInto(this.getClass(), this, container);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("What");
        newGameButton.setOnAction((e)->{
            try {
                Router.getApplicationRouter().route("NewGameView", new HashMap());
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
}
