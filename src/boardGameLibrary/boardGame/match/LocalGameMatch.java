package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.players.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-09-30.
 */
public class LocalGameMatch implements GameMatch{

    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    protected BoardMoveMaker board;

    private ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CellClickEvent> cellClickProperty = new SimpleObjectProperty<>();

    public LocalGameMatch(BoardMoveMaker board, Player[] players){
        this.board = board;

        for(Player player : players)
            this.players.add(player);

    }

    public void run() {
        turn(this.currentPlayer());

        this.board.getBoardMoveEventObjectProperty().addListener(e -> {
            System.out.println("Current player: " + this.currentPlayer().getName() + ", legal move? " + this.board.getBoardMoveEventObjectProperty().get().getMadeLegalMove());
            if(this.board.getBoardMoveEventObjectProperty().get().getNoMoreMoves()) {
                this.players.remove(this.currentPlayerIndex--);
                turn(this.nextPlayer());
            }
            else if(!this.board.getBoardMoveEventObjectProperty().get().getMadeLegalMove())
                turn(this.currentPlayer());
            else
                turn(this.nextPlayer());
        });
    }

    public void turn(Player player) {

        System.out.println("Current player: " + player.getName());
        player.makeMove(this.getBoardMoveMaker(), this.cellClickProperty(), this.legalMovesProperty());
    }

    public BoardMoveMaker getBoardMoveMaker(){
        return this.board;
    }

    public ObjectProperty<CellClickEvent> cellClickProperty(){
        return this.cellClickProperty;
    }

    public ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty(){
        return this.legalMovesProperty;
    }

    private Player nextPlayer(){
        this.currentPlayerIndex += 1;

        if(this.currentPlayerIndex == this.players.size())
            this.currentPlayerIndex = 0;

        return this.players.get(this.currentPlayerIndex);
    }

    private Player currentPlayer(){
        return this.players.get(this.currentPlayerIndex);
    }
}
