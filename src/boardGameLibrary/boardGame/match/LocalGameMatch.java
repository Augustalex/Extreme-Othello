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
public abstract class LocalGameMatch {

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
        ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty = new SimpleObjectProperty<>();
        madeMoveProperty.addListener(new MadeMoveListener(board));

        player.makeMove(madeMoveProperty, this.cellClickProperty());
    }

    private class MadeMoveListener implements ChangeListener<PlayerMadeMoveEvent> {

        private BoardMoveMaker boardMoveMaker;

        public MadeMoveListener(BoardMoveMaker boardMoveMaker){
            this.boardMoveMaker = boardMoveMaker;
        }


        public void changed(ObservableValue<? extends PlayerMadeMoveEvent> observable, PlayerMadeMoveEvent oldValue, PlayerMadeMoveEvent newValue) {
            this.boardMoveMaker.makeMove(observable.getValue().getPlayer(), observable.getValue().getMove());
            observable.removeListener(this);
        }
    }

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

    protected Player currentPlayer(){
        return this.players[this.currentPlayerIndex];
    }
}
