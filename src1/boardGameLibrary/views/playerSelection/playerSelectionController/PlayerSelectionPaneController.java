package boardGameLibrary.views.playerSelection.playerSelectionController;

import boardGameLibrary.views.dialogs.PlayerSelectionDialog;
import boardGameLibrary.views.playerSelection.PlayerSelectionBucket;
import boardGameLibrary.views.playerSelection.PlayerSelectionPane;
import boardGameLibrary.views.playerSelection.PlayerSelectionRow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A controller for handling events and actions for a {@link PlayerSelectionPane}.
 */
public class PlayerSelectionPaneController implements PlayerSelectionController {

    private ObjectProperty<PlayerSelectionPane> selectionPane = new SimpleObjectProperty<>();

    public PlayerSelectionPaneController(){
        this.selectionPane.addListener(c -> this.setConfirmationButtonListeners(this.selectionPane.get()));
    }

    @Override
    public PlayerSelectionController setPlayerSelectionPane(PlayerSelectionPane selectionPane) {
        this.selectionPane.set(selectionPane);

        return this;
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
}
