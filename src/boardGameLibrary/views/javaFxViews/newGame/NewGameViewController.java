package boardGameLibrary.views.javaFxViews.newGame;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.boardGame.match.LocalGameMatch;
import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import boardGameLibrary.viewModel.playerSelection.PlayerSelectionPane;
import boardGameLibrary.views.javaFxViews.FXMLViewController;
import boardGamePlugins.othello.board.OthelloBoard;
import boardGamePlugins.othello.board.OthelloBoardMoveMaker;
import boardGamePlugins.othello.players.GreedyAI;
import boardGamePlugins.othello.players.NaturalAI;
import boardGamePlugins.othello.players.RandomAI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utilities.router.Router;
import boardGameLibrary.views.javaFxViews.FXMLPaneLoader;
import utilities.router.paneRouter.PaneViewController;
import utilities.router.paneRouter.exceptions.NoContainerPaneSetException;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-09-29.
 */
public class NewGameViewController extends FXMLViewController{

    private static final String fxmlFileName = "NewGameView.fxml";

    @FXML
    private VBox newGameContainer;

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

        Player[] players = new Player[]{
            new LocalPlayer("August", Color.WHITE),
            new RandomAI("Björn", Color.BLACK),
            new RandomAI("Jacob", Color.RED),
            new RandomAI("Nick", Color.YELLOW),
            new RandomAI("Bosco", Color.ORANGE),
            new GreedyAI("Elvir", Color.BLUE),
            new RandomAI("Mackan", Color.TURQUOISE),
        };

        PlayerSelectionPane playerSelectionPane = new PlayerSelectionPane(players, 5);
        this.newGameContainer.getChildren().add(playerSelectionPane);

        playerSelectionPane.allPlayersSetProperty().addListener(e -> {
            //Create new match from selected players
            GameMatch gameMatch = GameMatch.createGameMatch("Othello", playerSelectionPane.getSelectedPlayers(), false);
            Map<String, Object> map = new HashMap<>();
            map.put("GameMatch", gameMatch);

            //Route to GameView with the new GameMatch object.
            Router.getApplicationRouter().route("GameView", map);
        });
    }
}
