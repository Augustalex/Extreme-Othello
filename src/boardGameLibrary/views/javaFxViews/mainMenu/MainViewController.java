package boardGameLibrary.views.javaFxViews.mainMenu;

import boardGameLibrary.views.javaFxViews.FXMLViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import utilities.router.Router;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-09-29.
 */
public class MainViewController extends FXMLViewController{

    private static final String fxmlFileName = "MainView.fxml";

    @FXML
    private Label mainTitle;

    @FXML
    private Button newGameButton;

    @FXML
    private HBox buttonContainer;

    public MainViewController(Pane container){
        super(container, MainViewController.fxmlFileName);
    }

    /**
     * This function runs when the FXML and CSS is loaded, and only then.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        strifeMenuButtonSize();
        setupButtonRouting();
    }

    private void strifeMenuButtonSize(){
        int numberOfButtons = this.buttonContainer.getChildren().filtered(n -> n instanceof Button).size();

        this.buttonContainer.getChildren().stream().filter(node -> node instanceof Button).forEach(node -> {
            Button button = (Button) node;
            button.minWidthProperty().bind(this.getContainer().widthProperty().divide(numberOfButtons));
            button.maxWidthProperty().bind(button.minWidthProperty());
        });
    }

    private void setupButtonRouting(){
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
