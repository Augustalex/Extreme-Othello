package boardGameLibrary.eventWrappers;

/**
 * Created by August on 2016-10-15.
 */
public class BoardMoveEvent {

    private boolean madeLegalMove;

    public BoardMoveEvent(boolean madeLegalMove){
        this.madeLegalMove = madeLegalMove;
    }

    public boolean getMadeLegalMove(){
        return this.madeLegalMove;
    }
}
