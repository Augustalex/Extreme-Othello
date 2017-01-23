package boardGameLibrary.views.playerSelection.playerSelectionController;

import boardGameLibrary.views.playerSelection.PlayerSelectionPane;
import boardGameLibrary.views.playerSelection.PlayerSelectionRow;
import communication.GameServer;
import communication.OneTimeChangeListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;

/**
 * Sets up change listeners for when a player is selected.
 *
 * Also connects to players that has been selected and asks for "Join game" confirmation.
 */
public class OnlinePlayerSelectionPaneController implements PlayerSelectionController {

    private ObjectProperty<PlayerSelectionPane> selectionPane = new SimpleObjectProperty<>();
    private GameServer gameServer;

    public OnlinePlayerSelectionPaneController(GameServer server){
        this.gameServer = server;

        this.selectionPane.addListener((observable, oldValue, newValue) -> {
            this.selectionPane.get().getPlayerSelectionRows().parallelStream()
                    .forEach(row -> setupPlayerSelectionListener(this.gameServer, row));
        });
    }

    public void setupPlayerSelectionListener(GameServer server, PlayerSelectionRow row){
        row.getSelectionBox().valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null)
                return;

            System.out.println("Has set player selection listener.");
            server.requestConnection(newValue).onDelivery(new OneTimeChangeListener<>((observable1, oldValue1, delivery) -> {
                System.out.println("DELIVERY HAS ARRIVED: " + Arrays.toString(delivery));
                row.getConfirmationButton().confirmedSelectionProperty().set(row.getSelectionBox().getValue());
            }));
        });
    }

    @Override
    public PlayerSelectionController setPlayerSelectionPane(PlayerSelectionPane selectionPane) {
        this.selectionPane.set(selectionPane);

        return this;
    }
}
