package boardGameLibrary.eventWrappers;

/**
 * Created by August on 2016-10-15.
 */
public class BoardMoveEvent {

    private boolean madeLegalMove;
    private boolean noMoreMoves;

    public BoardMoveEvent(boolean madeLegalMove, boolean noMoreMoves){
        this.madeLegalMove = madeLegalMove;
        this.noMoreMoves = noMoreMoves;
    }

    public boolean getMadeLegalMove(){
        return this.madeLegalMove;
    }

    public boolean getNoMoreMoves(){
        return this.noMoreMoves;
    }
}
