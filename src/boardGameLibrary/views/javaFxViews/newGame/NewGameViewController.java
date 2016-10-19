package boardGameLibrary.views.javaFxViews.newGame;

import boardGameLibrary.views.javaFxViews.FXMLViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import utilities.router.Router;
import boardGameLibrary.views.javaFxViews.FXMLPaneLoader;
import utilities.router.paneRouter.PaneViewController;
import utilities.router.paneRouter.exceptions.NoContainerPaneSetException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-09-29.
 */
public class NewGameViewController extends FXMLViewController{

    private static final String fxmlFileName = "NewGameView.fxml";

    @FXML
    private Label firstPlayerBoxLabel;

    @FXML
    private ComboBox firstPlayerBox;

    @FXML
    private Label secondPlayerBoxLabel;

    @FXML
    private ComboBox secondPlayerBox;

    @FXML
    private Button back;

    public NewGameViewController(Pane container) {
        super(container, NewGameViewController.fxmlFileName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupPlayerComboBoxes();

        back.setOnAction((e)-> Router.getApplicationRouter().previous());
    }

    private void setupPlayerComboBoxes(){
        String[] playerChoices = new String[]{
                "Local Player",
                "Remote Player",
                "Computer AI",
                "Bob Smith"
        };

        firstPlayerBoxLabel.setText("Choose first player: ");
        firstPlayerBox.getItems().setAll(playerChoices);

        secondPlayerBoxLabel.setText("Choose second player: ");
        secondPlayerBox.getItems().setAll(playerChoices);
    }
}
