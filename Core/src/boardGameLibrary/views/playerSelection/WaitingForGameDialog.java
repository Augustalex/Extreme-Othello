package boardGameLibrary.views.playerSelection;

import javafx.beans.NamedArg;
import javafx.scene.control.Alert;

/**
 * Alert box for when a Host has been selected in
 * New Game view. That is, a host which is not the
 * local human computer player.
 */
public class WaitingForGameDialog extends Alert {

    public WaitingForGameDialog(@NamedArg("alertType") AlertType alertType) {
        super(alertType);

        this.setHeaderText("Waiting for game to start...");
        this.setContentText("When all players have joined. The game will start.");
    }
}
