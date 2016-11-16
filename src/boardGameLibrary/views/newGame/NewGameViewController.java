package boardGameLibrary.views.newGame;

import boardGameLibrary.boardGame.match.MatchSetup;
import boardGameLibrary.playerProfileStore.PlayerProfileStore;
import boardGameLibrary.players.Player;
import boardGameLibrary.views.playerSelection.NumberOfPlayersSelection;
import boardGameLibrary.views.playerSelection.PlayerSelectionPane;
import boardGameLibrary.views.FXMLViewController;
import boardGamePlugins.othello.players.GreedyAI;
import boardGamePlugins.othello.players.NaturalAI;
import communication.GameServer;
import communication.receiver.delivery.PropertyDelivery;
import communication.requests.GameRequest;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import utilities.router.Router;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by August on 2016-09-29.
 */
public class NewGameViewController extends FXMLViewController{

    private static final String fxmlFileName = "NewGameView.fxml";

    private PlayerProfileStore store;
    private PlayerSelectionPane selectionPane = null;

    private GameServer server;

    @FXML
    private VBox newGameContainer;

    @FXML
    private Button back;

    public NewGameViewController(Pane container, PlayerProfileStore store) {
        super(container, NewGameViewController.fxmlFileName);

        try {
            this.server = new GameServer(1337, 1337);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.store = store;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupButtonActions();

        Player[] players = getAvailablePlayers();

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

        //Sets up what happens when the number of players are selected.
        numberOfPlayersSelection.selectedValue().addListener(e -> {
            System.out.println("IT WORKED!");
            this.newGameContainer.getChildren().remove(this.getSelectionPane());

            PlayerSelectionPane selectionPane = new PlayerSelectionPane(players, numberOfPlayersSelection.selectedValue().get());
            setSelectionPane(selectionPane);

            selectionPane.chosenPlayersProperty().onAllPlayersSelected(selectionPaneListener);

            this.newGameContainer.getChildren().add(selectionPane);
        });

    }

    private void confirmPlayer(Player player){

    }

    private void unselectPlayer(Player player){

    }

    private void setupButtonActions(){
        back.setOnAction((e)-> Router.getApplicationRouter().previous());
    }

    public void connectPlayer(Player player){

        PropertyDelivery<GameRequest> request;
/*
        if(this.server.canConnect(player))
            request = this.server.requestConnection(player);
        else
            return;

        request.onDelivery(new OneTimeChangeListener<>((observable, oldValue, newValue) -> {
            if(newValue)
                this.confirmPlayer(player);
            else
                this.unselectPlayer(player);
        }));
*/

    }

    private Player[] getAvailablePlayers() {
        Player[] p = this.store.toPlayers();

        List<Player> allPlayers = new ArrayList<>();
        Collections.addAll(allPlayers, p);
        allPlayers.add(new NaturalAI("Jacob", Color.BURLYWOOD));
        allPlayers.add(new GreedyAI("Patrick", Color.RED));
        allPlayers.add(new GreedyAI("Felix", Color.ALICEBLUE));
        allPlayers.add(new NaturalAI("Mackan", Color.TURQUOISE));

        Player[] activePlayers = this.server.activePlayersManager().getActivePlayers();
        allPlayers.addAll(Stream.of(activePlayers).collect(Collectors.toList()));

        return allPlayers.stream().toArray(Player[]::new);
    }

    public void setSelectionPane(PlayerSelectionPane pane){
        this.selectionPane = pane;
    }

    public PlayerSelectionPane getSelectionPane(){
        return this.selectionPane;
    }

}


        /*Player[] players = new Player[]{
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

        };*/
