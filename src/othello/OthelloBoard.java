package othello;

import boardGame.GameBoard;
import boardGame.Move;
import boardGame.PlayerAction;
import boardGame.player.Player;
import boardGame.player.VoidPlayer;
import javafx.scene.paint.Color;
import othello.exceptions.IllegalMoveException;

import java.awt.Point;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloBoard extends GameBoard {
    private static int width = 8;
    private static int height = 8;
    public OthelloBoard() {
        super(OthelloBoard.width, OthelloBoard.height);

        for(int y = 0; y < OthelloBoard.height; y++)
            for(int x = 0; x < OthelloBoard.width; x++)
                this.board[x][y] = new OthelloPawn(new VoidPlayer(), new Color(0,0,0,0));
    }

    @Override
    public boolean isLegalMove(Player player, Move move) {
        if(move.getMove().length < 1)
            throw new IllegalMoveException(move);

        PlayerAction action = move.getMove()[0];
        Point startPosition = new Point(action.getX(), action.getY());

        for(Direction direction : Direction.values()){
            Point position = movePoint(direction, startPosition);
            while(this.withinBounds(position) && !this.isEmpty(position)){
                if(this.getPawn(position).getOwner().equals(player))
                    return true;

                position = movePoint(direction, position);
            }
        }

        return false;
    }

    @Override
    public Move getAvailableMoves() {
        return null;
    }

    @Override
    public void makeMove(Player player, Move move) {
        PlayerAction action = move.getMove()[0];
        Point startPosition = new Point(action.getX(), action.getY());

        this.getPawn(startPosition).setOwner(player);
        for(Direction direction : Direction.values()) {
            findMove(player, movePoint(direction, startPosition), direction);
        }
    }

    private void makeMoveThreadsTest(){

    }

    private void findMove(Player player, Point position, Direction direction){
        while(this.withinBounds(position) && !this.isEmpty(position)){
            if(this.getPawn(position).getOwner().equals(player))
                makeMoveHelper(player, movePoint(direction.getOppositeDirection(), position), direction.getOppositeDirection());
            position = movePoint(direction, position);
        }
    }

    private void makeMoveHelper(Player player, Point position, Direction direction){
        while(!this.getPawn(position).getOwner().equals(player)){
            this.getPawn(position).setOwner(player);

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

        this.board[3][3] = new OthelloPawn(white, Color.WHITE);
        this.board[4][4] = new OthelloPawn(white, Color.WHITE);
        this.board[3][4] = new OthelloPawn(black, Color.BLACK);
        this.board[4][3] = new OthelloPawn(black, Color.BLACK);
    }

}
