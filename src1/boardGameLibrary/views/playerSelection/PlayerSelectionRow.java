package boardGameLibrary.views.playerSelection;

import boardGameLibrary.players.Player;
import boardGameLibrary.views.playerSelection.selectionConfirmation.SelectionConfirmationButton;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

/**
 * A selection box for picking a player as well as a confirmation box
 * for confirming the selection.
 *
 * Acts as a wrapper element that contains and aligns the objects horizontally.
 */
public class PlayerSelectionRow extends HBox {

    private PlayerSelectionBox selectionBox;
    private SelectionConfirmationButton<Player> confirmationButton;

    public PlayerSelectionRow(ObservableList<Player> options){
        this.selectionBox = new PlayerSelectionBox(options);
        this.confirmationButton = new SelectionConfirmationButton<>(this.selectionBox);

        this.getChildren().addAll(selectionBox, confirmationButton);
    }

    public PlayerSelectionBox getSelectionBox(){
        return this.selectionBox;
    }

    public SelectionConfirmationButton<Player> getConfirmationButton(){
        return this.confirmationButton;
    }
}
