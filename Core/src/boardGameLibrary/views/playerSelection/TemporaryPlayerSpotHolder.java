package boardGameLibrary.views.playerSelection;

import boardGameLibrary.players.Player;

/**
 * Temporarily hold a PlayerSelectionRow until the spot
 * is confirmed.
 *
 * When a row is on hold, a player is set as selected,
 * but the checkbox is disabled.
 */
public class TemporaryPlayerSpotHolder {

    private PlayerSelectionRow playerSelectionRow;

    public TemporaryPlayerSpotHolder(PlayerSelectionRow playerSelectionRow){
        this.playerSelectionRow = playerSelectionRow;
    }

    /**
     * Will select the player in the selection row as well as
     * disable the checkbox until the confirm method in this class
     * is run.
     * @param player
     */
    public void hold(Player player){
        playerSelectionRow.getSelectionBox().getSelectionModel().select(player);
        playerSelectionRow.getConfirmationButton().setDisable(true);
    }

    /**
     * Enables the checkbox and also checks it (set is as selected).
     */
    public void confirm(){
        playerSelectionRow.getConfirmationButton().setDisable(false);
        playerSelectionRow.getConfirmationButton().setSelected(true);
    }

    /**
     * Will clear the selection and re-enable the checkbox.
     */
    public void unhold(){
        playerSelectionRow.getSelectionBox().getSelectionModel().clearSelection();
        playerSelectionRow.getConfirmationButton().setDisable(false);
        playerSelectionRow.getConfirmationButton().setSelected(false);
    }
}
