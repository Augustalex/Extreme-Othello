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
        turn(this.nextPlayer());

        this.board.getBoardMoveEventObjectProperty().addListener(e -> {
            if(!this.board.getBoardMoveEventObjectProperty().get().getMadeLegalMove())
                turn(this.currentPlayer());
            else
                turn(this.nextPlayer());
        });
    }

    public void turn(Player player) {
        System.out.println("Current player: " + player.getName());
        ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty = new SimpleObjectProperty<>();

        ChangeListener<PlayerMadeMoveEvent> madeMoveListener = (observableValue, oldValue, newValue) -> {
            this.board.makeMove(observableValue.getValue().getPlayer(), observableValue.getValue().getMove());
        };

        madeMoveProperty.addListener(e -> {
            madeMoveListener.changed(madeMoveProperty, null, madeMoveProperty.get());
            madeMoveProperty.removeListener(madeMoveListener);
        });

        player.makeMove(madeMoveProperty, this.cellClickProperty());
    }

    public BoardMoveMaker getBoardMoveMaker(){
        return this.board;
    }

    public ObjectProperty<CellClickEvent> cellClickProperty(){
        return this.cellClickProperty;
    }

    private Player nextPlayer(){
        Player nextPlayer = this.players[this.currentPlayerIndex++];

        if(this.currentPlayerIndex == this.players.length)
            this.currentPlayerIndex = 0;

        return nextPlayer;
    }

    private Player currentPlayer(){
        return this.players[this.currentPlayerIndex];
    }
}
