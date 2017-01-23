package boardGameLibrary.views.playerSelection.playerSelectionController;

import boardGameLibrary.players.Player;
import boardGameLibrary.views.dialogs.PlayerSelectionDialog;
import boardGameLibrary.views.newGame.LobbyConfiguration;
import boardGameLibrary.views.playerSelection.PlayerSelectionBucket;
import boardGameLibrary.views.playerSelection.PlayerSelectionPane;
import boardGameLibrary.views.playerSelection.PlayerSelectionRow;
import boardGameLibrary.views.playerSelection.selectionConfirmation.SelectionConfirmationButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A controller for handling events and actions for a {@link PlayerSelectionPane}.
 */
public class PlayerSelectionPaneController implements PlayerSelectionController {

    private ObjectProperty<PlayerSelectionPane> selectionPane = new SimpleObjectProperty<>();

    public PlayerSelectionPaneController(){
        this.selectionPane.addListener((observable, oldValue, newValue) -> {
            this.setConfirmationButtonListeners(newValue);
        });
    }

    @Override
    public PlayerSelectionController setPlayerSelectionPane(PlayerSelectionPane selectionPane) {
        this.selectionPane.set(selectionPane);

        return this;
    }

    private void setSelectionListener(){
        for(PlayerSelectionRow row : selectionPane.get().getPlayerSelectionRows()){
            row.getSelectionBox().selectionModelProperty().addListener((observable, oldValue, selectedPlayer) -> {

            });
        }
    }

    private void setConfirmationButtonListeners(PlayerSelectionPane selectionPane){

        PlayerSelectionBucket chosenPlayers = selectionPane.chosenPlayersProperty();

        selectionPane.getPlayerSelectionRows().parallelStream()
                .map(PlayerSelectionRow::getConfirmationButton)
                .forEach(button -> button.confirmedSelectionProperty().addListener((observable, oldValue, newValue) -> {
                    if (button.isSelected()) {
                        if (chosenPlayers.contains(newValue)) {
                            PlayerSelectionDialog.displayAndWait("Player already selected.");
                            button.reset();
                        } else if (newValue != null)
                            chosenPlayers.add(newValue);
                    } else
                        chosenPlayers.remove(oldValue);
                }));
    }

//    private void setHostChangeListener(PlayerSelectionPane selectionPane){
//        SelectionConfirmationButton<Player> confirmationButton = selectionPane.getHostSelectionRow().getConfirmationButton()
//
//        confirmationButton.confirmedSelectionProperty().addListener((observable, oldValue, selectedPlayer) -> {
//            if(confirmationButton.isSelected()){
//                LobbyConfiguration lobbyConfiguration = selectionPane.getLobbyConfiguration();
//
//                if(selectedPlayer.getName().equals(lobbyConfiguration.getHostPlayer().getName())){
//                    setupLocalGameSession();
//                }
//                else{
//                    setupRemoteGameSession();
//                }
//            }
//
//        });
//    }
//
//    private void setupRemoteGameSession() {
//
//    }
//
//    private void setupLocalGameSession() {
//        selectionPane.get().displayPlayerSelectionRows();
//    }
}
