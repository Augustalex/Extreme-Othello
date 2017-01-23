package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import hostDetails.Host;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class RemotePlayer extends Player {

    public final static int clientPort = 3000;
    private final Host connectionDetails;
    private String ip;

    public RemotePlayer(String name, Color color, Host connectionDetails) {
        super(name, color);
        this.connectionDetails = connectionDetails;
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties) {

    }

    @Override
    public Host getConnectionDetails() {
        return null;
    }

    public RemotePlayer setIP(String ipv4){
        this.ip = ipv4;
        return this;
    }
}
