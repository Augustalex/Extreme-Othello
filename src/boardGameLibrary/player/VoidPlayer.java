package boardGameLibrary.player;

import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-10-02.
 */
public class VoidPlayer extends Player{

    public VoidPlayer() {
        super("Void", Color.TRANSPARENT);
    }

    @Override
    public void makeMove(ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty, ObjectProperty<CellClickEvent> cellClickProperty) {

    }
}
