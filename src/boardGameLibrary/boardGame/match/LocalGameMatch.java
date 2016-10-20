package boardGameLibrary.boardGame.match;

import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import boardGameLibrary.player.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by August on 2016-09-30.
 */
public class LocalGameMatch implements GameMatch{

    private Player[] players;
    private int currentPlayerIndex = 0;

    protected BoardMoveMaker board;

    private ObjectProperty<CellClickEvent> cellClickProperty = new SimpleObjectProperty<>();

    public LocalGameMatch(BoardMoveMaker board, Player[] players){
        this.board = board;
        this.players = players;
    }

    public void run() {
        turn(this.currentPlayer());

        this.board.getBoardMoveEventObjectProperty().addListener(e -> {
            System.out.println("Current player: " + this.currentPlayer().getName() + ", legal move? " + this.board.getBoardMoveEventObjectProperty().get().getMadeLegalMove());
            if(!this.board.getBoardMoveEventObjectProperty().get().getMadeLegalMove())
                turn(this.currentPlayer());
            else
                turn(this.nextPlayer());
        });
    }

    public void turn(Player player) {
        System.out.println("Current player: " + player.getName());
        player.makeMove(this.getBoardMoveMaker(), this.cellClickProperty());
    }

    public BoardMoveMaker getBoardMoveMaker(){
        return this.board;
    }

    public ObjectProperty<CellClickEvent> cellClickProperty(){
        return this.cellClickProperty;
    }

    private Player nextPlayer(){
        this.currentPlayerIndex += 1;

        if(this.currentPlayerIndex == this.players.length)
            this.currentPlayerIndex = 0;

        return this.players[this.currentPlayerIndex];
    }

    private Player currentPlayer(){
        return this.players[this.currentPlayerIndex];
    }
}
