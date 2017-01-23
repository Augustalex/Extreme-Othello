package boardGameLibrary.views.playerSelection;

import boardGameLibrary.players.Player;
import boardGameLibrary.views.dialogs.PlayerSelectionDialog;
import boardGameLibrary.views.newGame.LobbyConfiguration;
import boardGameLibrary.views.playerSelection.playerSelectionController.PlayerSelectionController;
import boardGameLibrary.views.playerSelection.playerSelectionController.PlayerSelectionPaneController;
import boardGameLibrary.views.playerSelection.selectionConfirmation.SelectionConfirmationButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSelectionPane extends VBox {

    private PlayerSelectionBucket chosenPlayers = null;
    private ObservableList<Player> playerOptions;
    private LobbyConfiguration lobbyConfiguration;

    private List<PlayerSelectionRow> rows = new ArrayList<>();
    private PlayerSelectionController selectionController = new PlayerSelectionPaneController().setPlayerSelectionPane(this);
//
//    private VBox hostSelectionHolder = new VBox();
//    private VBox playerSelectionRowsHolder = new VBox();
//    private PlayerSelectionRow hostSelectionRow;

    public PlayerSelectionPane(Player[] playerOptions, LobbyConfiguration lobbyConfiguration){
        this.playerOptions = FXCollections.observableArrayList(playerOptions);
        this.lobbyConfiguration = lobbyConfiguration;
        this.initialize(lobbyConfiguration.getNumberOfPlayers());
    }

    public PlayerSelectionBucket chosenPlayersProperty(){
        return this.chosenPlayers;
    }

    public PlayerSelectionController getSelectionController(){
        return this.selectionController;
    }

    public void setSelectionController(PlayerSelectionController selectionController){
        this.selectionController = selectionController;
        this.selectionController.setPlayerSelectionPane(this);
    }

    public List<PlayerSelectionRow> getPlayerSelectionRows(){
        return this.rows;
    }

//    public PlayerSelectionRow getHostSelectionRow(){
//        return this.hostSelectionRow;
//    }

    public LobbyConfiguration getLobbyConfiguration(){
        return this.lobbyConfiguration;
    }

    private void initialize(int numberOfPlayers){
        this.chosenPlayers = new PlayerSelectionBucket(numberOfPlayers);
//
//        this.getChildren().addAll(
//                hostSelectionHolder,
//                playerSelectionRowsHolder
//        );

//        setupHostSelectionBox();

        displayPlayerSelectionRows();
    }

    private void displayPlayerSelectionRows(){
        for(int i = 0 ; i < this.lobbyConfiguration.getNumberOfPlayers(); i++){
            addPlayerSelectionBox();
        }

    }

  /*  private void setupHostSelectionBox(){
        PlayerSelectionRow selectionRow = new PlayerSelectionRow(this.playerOptions);

        hostSelectionHolder.getChildren().add(selectionRow);
        this.hostSelectionRow = selectionRow;
    }*/

    private void addPlayerSelectionBox(){
        PlayerSelectionRow selectionRow = new PlayerSelectionRow(this.playerOptions);

        this.getChildren().add(selectionRow);
        this.rows.add(selectionRow);
    }

    /*

    private Player getPlayerFromOptionName(String playerName){
        Player[] choice = this.playerOptions.parallelStream()
                .filter(player -> player.getName().equals(playerName))
                .toArray(Player[]::new);

        if(choice.length == 0)
            throw new NoSuchPlayerException();
        else if(choice.length > 1)
            throw new NonUniquePlayerNameException();
        else
            return choice[0];
    }
     */
}
