package boardGamePlugins.othello.board;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.board.Direction;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.boardGame.move.PlayerAction;
import boardGameLibrary.player.Player;
import boardGamePlugins.othello.pawn.OthelloPawn;
import boardGamePlugins.othello.board.exceptions.IllegalMoveException;

import java.awt.Point;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloBoardMoveMaker extends BoardMoveMaker {

    /**
     * Recieves an instantiated {@link OthelloBoard} that is accessible from
     * a protected field inside this class.
     * @param board
     */
    public OthelloBoardMoveMaker(OthelloBoard board) {
        super(board);
    }

    private final Object key = new Object();

    /**
     * Checks if a move made by a player is legal.
     *
     * For all possible directions it passes down a straight path from the starting position.
     * If a player owned pawn is crossed beyond a distance of 1 cell, it is a legal move. Otherwise,
     * it is a non-legal move.
     *
     * @param player
     * @param move
     * @return
     */
    @Override
    public boolean isLegalMove(Player player, Move move) {
        if(move.getMove().length < 1)
            throw new IllegalMoveException(move);

        PlayerAction action = move.getMove()[0];
        Point startPosition = new Point(action.getX(), action.getY());

        if(this.board.withinBounds(startPosition) && this.board.isEmpty(startPosition))
            for(Direction direction : Direction.values()){

                Point position = movePoint(direction, startPosition);
                int distance = 1;

                while(this.board.withinBounds(position) && !this.board.isEmpty(position)){
                    if(this.board.getPawn(position).getOwner().equals(player)) {
                        if (distance > 1)
                            return true;
                        else
                            continue;
                    }

                    position = movePoint(direction, position);
                    distance++;
                }
            }

        return false;
    }

    /**
     * Returns an array of available {@link Move}s from which a player can make a decision on which move to apply.
     * @return
     */
    @Override
    public Move[] getAvailableMoves() {
        return null;
    }

    @Override
    public void makeMove(Player player, Move move) {
        synchronized (this.key) {
            if (!isLegalMove(player, move))
                this.setMadeMove(false);
            else {

                PlayerAction action = move.getMove()[0];
                Point startPosition = new Point(action.getX(), action.getY());

                this.board.setPawn(startPosition, new OthelloPawn(player));

                for (Direction direction : Direction.values()) {
                    findMove(player, movePoint(direction, startPosition), direction);
                }

                this.setMadeMove(true);
            }
        }
    }

    private void findMove(Player player, Point position, Direction direction){
        while(this.board.withinBounds(position) && !this.board.isEmpty(position)){
            if(this.board.getPawn(position).getOwner().equals(player))
                flipPawns(player, movePoint(direction.getOppositeDirection(), position), direction.getOppositeDirection());
            position = movePoint(direction, position);
        }
    }

    private void flipPawns(Player player, Point position, Direction direction){
        while(!this.board.getPawn(position).getOwner().equals(player)){
            this.board.setPawn(position, new OthelloPawn(player));

            for(Direction findDirection : Direction.values())
                if(!findDirection.equals(direction) || !findDirection.equals(direction.getOppositeDirection()))
                    findMove(player, movePoint(findDirection, position), findDirection);

            position = movePoint(direction, position);
        }
    }

    private Point movePoint(Direction direction, Point origin){
        int speed = 1;
        return new Point((int)Math.round(origin.x + speed * Math.cos(direction.getRadianAngle())),
                (int)Math.round(origin.y + speed * Math.sin(direction.getRadianAngle())));
    }

    @Override
    public void setStartPawns(Player[] players){
        Player white = players[0];
        Player black = players[1];

        this.board.setPawn(new Point(3, 3), new OthelloPawn(white));
        this.board.setPawn(new Point(4,4), new OthelloPawn(white));
        this.board.setPawn(new Point(3, 4), new OthelloPawn(black));
        this.board.setPawn(new Point(4, 3), new OthelloPawn(black));
    }

}
