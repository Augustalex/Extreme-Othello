package boardGameLibrary.viewModel.playerSelection;

import boardGameLibrary.players.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by August on 2016-10-23.
 */
public class SelectionConfirmationButton extends CheckBox{

    private ObjectProperty<Player> confirmedPlayer = new SimpleObjectProperty<>();

    public SelectionConfirmationButton(PlayerSelectionBox playerSelectionBox){
        this.selectedProperty().addListener(e -> {
            if(this.selectedProperty().get())
                this.confirmedPlayer.set(playerSelectionBox.getValue());
            else
                this.confirmedPlayer.set(null);
        });
    }

    public ObjectProperty<Player> confirmedPlayerProperty(){
        return this.confirmedPlayer;
    }
}
