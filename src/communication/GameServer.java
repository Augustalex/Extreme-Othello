package communication;

import boardGameLibrary.players.Player;
import communication.connection.inputConnections.RequestInputSocket;
import communication.receiver.delivery.Delivery;
import communication.requests.GameRequest;
import storage.activePlayersManagement.IActivePlayersManagement;
import storage.activePlayersManagement.OfflineActivePlayersManager;

import java.io.IOException;

/**
 * Created by August on 2016-10-30.
 */
public class GameServer {

    private RequestInputSocket requestConnection;
    private RequestReceiver requestReceiver;
    private IActivePlayersManagement activePlayersManager = new OfflineActivePlayersManager();

    public GameServer(int port) throws IOException {
        this.requestConnection = new RequestInputSocket(port);
        this.requestReceiver = new RequestReceiver(this.requestConnection);
    }

    public IActivePlayersManagement activePlayersManager(){
        return this.activePlayersManager;

    }

    public boolean canConnect(Player player){
        return false;
    }

    public Delivery<GameRequest> requestConnection(Player player){
        return null;
    }



}
