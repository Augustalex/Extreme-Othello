package boardGameLibrary.viewModel.playerSelection;

import boardGameLibrary.players.Player;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-10-23.
 */
public class PlayerSelectionBox extends ComboBox<Player> {

    public PlayerSelectionBox(ObservableList<Player> options) {
        this.setItems(options);

        this.setCellFactory(e -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player p, boolean empty) {
                super.updateItem(p, empty);

                if (p != null) {
                    setText(p.toString());
                    setTextFill(Color.BLACK);
                }
                else
                    setText(null);

            }
        });
    }
}
