package boardGameLibrary.player;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class LocalPlayer extends Player {

    public LocalPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty, ObjectProperty<CellClickEvent> cellClickProperty) {
        new Thread(new PlayerMoveGetter(this, cellClickProperty, madeMoveProperty)).start();
    }

}
