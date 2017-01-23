package boardGameLibrary.views.playerSelection;

import boardGameLibrary.players.Player;
import boardGameLibrary.views.playerSelection.selectionConfirmation.SelectionConfirmationButton;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

/**
 * Created by August on 2017-01-22.
 */
public class PlayerHostSelectionRow extends HBox{

    private PlayerSelectionBox selectionBox;

    public PlayerHostSelectionRow(ObservableList<Player> options){

    }

    public PlayerSelectionBox getSelectionBox(){
        return this.selectionBox;
    }

    public SelectionConfirmationButton<Player> getConfirmationButton(){
        return this.confirmationButton;
    }
}
