package boardGameLibrary.boardGame.match;

import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.player.Player;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by August on 2016-09-30.
 */
public abstract class GameMatch {

    private Player[] players;
    private int currentPlayerIndex = 0;

    protected BoardMoveMaker board;

    private ObjectProperty<CellClickEvent> cellClickProperty = new SimpleObjectProperty<>();

    public GameMatch(BoardMoveMaker board, Player[] players){
        this.board = board;
        this.players = players;
    }

    public void run() {
        turn(this.board);

        this.board.getBoardMoveEventObjectProperty().addListener(e -> {
            if(!this.board.getBoardMoveEventObjectProperty().get().getMadeLegalMove())
                this.previousPlayer();

            turn(this.board);
        });
    }

    protected abstract void turn(BoardMoveMaker board);

    public BoardMoveMaker getBoardMoveMaker(){
        return this.board;
    }

    public ObjectProperty<CellClickEvent> cellClickProperty(){
        return this.cellClickProperty;
    }

    protected Player nextPlayer(){
        Player nextPlayer = this.players[this.currentPlayerIndex++];

        if(this.currentPlayerIndex == this.players.length)
            this.currentPlayerIndex = 0;

        return nextPlayer;
    }

    protected Player previousPlayer(){
        this.currentPlayerIndex--;

        if(this.currentPlayerIndex < 0)
            this.currentPlayerIndex = this.players.length-1;

        return this.players[this.currentPlayerIndex];
    }
}
