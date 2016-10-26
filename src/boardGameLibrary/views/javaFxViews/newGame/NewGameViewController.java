package boardGameLibrary.views.javaFxViews.newGame;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.boardGame.match.LocalGameMatch;
import boardGameLibrary.boardGame.match.MatchSetup;
import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import boardGameLibrary.viewModel.playerSelection.NumberOfPlayersSelection;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
            new LocalPlayer("Björn", Color.BLACK),
            new NaturalAI("Jacob", Color.BURLYWOOD),
            new NaturalAI("Nick", Color.YELLOW),
            new NaturalAI("Bosco", Color.ORANGE),
            new GreedyAI("Elvir", Color.BLUE),
            new RandomAI("Mackan", Color.TURQUOISE),
            new RandomAI("Carlos", Color.DARKCYAN),
            new GreedyAI("Patrick", Color.RED),
            new NaturalAI("Simon", Color.PURPLE),
            new RandomAI("Viktor", Color.ANTIQUEWHITE),
            new GreedyAI("Victor", Color.ALICEBLUE),
            new NaturalAI("Robin", Color.AZURE),
            new NaturalAI("Johan", Color.CADETBLUE),
            new NaturalAI("Mössjohan", Color.DARKMAGENTA),
            new NaturalAI("Okan", Color.PINK),
            new NaturalAI("Alex", Color.DEEPPINK),
            new NaturalAI("Sebastian", Color.YELLOWGREEN),
            new NaturalAI("Andreas", Color.TOMATO),
            new NaturalAI("Kristoffer", Color.THISTLE)

        };

        NumberOfPlayersSelection numberOfPlayersSelection = new NumberOfPlayersSelection(16);
        this.newGameContainer.getChildren().add(numberOfPlayersSelection);

        final ChangeListener<Boolean> selectionPaneListener = (observable, oldValue, newValue) -> {
            //Create new match from selected players
            MatchSetup setup = new MatchSetup(
                    "Othello",
                    getSelectionPane().chosenPlayersProperty().removeAndReturnAllSelectedPlayers()
            );

            Map<String, Object> map = new HashMap<>();
            map.put("MatchSetup", setup);

            //Route to GameView with the new GameMatch object.
            Router.getApplicationRouter().route("GameView", map);
        };

        numberOfPlayersSelection.selectedValue().addListener(e -> {
            System.out.println("IT WORKED!");
            this.newGameContainer.getChildren().remove(this.getSelectionPane());

            PlayerSelectionPane selectionPane = new PlayerSelectionPane(players, numberOfPlayersSelection.selectedValue().get());
            setSelectionPane(selectionPane);

            selectionPane.chosenPlayersProperty().onAllPlayersSelected(selectionPaneListener);

            this.newGameContainer.getChildren().add(selectionPane);
        });
    }

    private PlayerSelectionPane selectionPane = null;

    public void setSelectionPane(PlayerSelectionPane pane){
        this.selectionPane = pane;
    }

    public PlayerSelectionPane getSelectionPane(){
        return this.selectionPane;
    }
}
