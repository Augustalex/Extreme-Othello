package communication;

import boardGameLibrary.players.Player;
import communication.connection.inputConnections.RequestInputSocket;
import communication.receiver.delivery.Delivery;
import communication.requests.GameRequest;
import communication.requests.Request;
import communication.requests.RequestCompiler;
import communication.sender.Package;
import communication.sender.Sender;
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

    private int port;

    public GameServer(int port) throws IOException {
        this.port = port;

        this.requestConnection = new RequestInputSocket(port);
        this.requestReceiver = new RequestReceiver(this.requestConnection);
    }

    public IActivePlayersManagement activePlayersManager(){
        return this.activePlayersManager;

    }

    public Delivery<Request[]> requestConnection(Player player){
        ConnectionDetails connectionDetails = this.activePlayersManager.getActivePlayerConnectionDetails(player);

        try {
            Sender sender = new Sender(connectionDetails, this.port);
            sender.send(
                    new Package()
                    .senderAddress("192.168.1.4")
                    .receiverAddress(connectionDetails.hostname)
                    .setRequests(new String[]{RequestCompiler.encodeToString(new GameRequest())})
            );
            System.out.println("Package sent.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.requestReceiver.expectRequest();
    }



}
