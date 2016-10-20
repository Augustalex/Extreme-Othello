package boardGameLibrary.player;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.eventWrappers.CellClickEvent;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by August on 2016-09-30.
 */
public class ComputerPlayer extends Player{

    public ComputerPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, ObjectProperty<CellClickEvent> cellClickProperty) {
        Move[] legalMoves = boardMoveMaker.getAvailableMoves(this);

        if(legalMoves.length == 0)
            throw new RuntimeException("No available moves for Player: " + this.getName());

        int choice = ThreadLocalRandom.current().nextInt(0, legalMoves.length);

        new Thread(() -> {
            try {
                Thread.sleep(800);
                Platform.runLater(() -> boardMoveMaker.makeMove(this, legalMoves[choice]));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
