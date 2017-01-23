package communication;

import boardGameLibrary.players.Player;
import communication.connection.inputConnections.RequestInputSocket;
import communication.receiver.delivery.Delivery;
import communication.requests.GameRequest;
import communication.requests.Request;
import communication.requests.RequestCompiler;
import communication.sender.Package;
import communication.sender.PackageCompiler;
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

        this.requestReceiver.loomingDelivery(new Request[]{new GameRequest()});
    }

    public IActivePlayersManagement activePlayersManager(){
        return this.activePlayersManager;

    }

    public boolean canConnect(Player player){
        return false;
    }

    public Delivery<Request[]> requestConnection(Player player){
        ConnectionDetails connectionDetails = this.activePlayersManager.getActivePlayerConnectionDetails(player);

        try {
            Sender sender = new Sender(connectionDetails, this.port);

            Package payload = new Package()
                    .senderAddress("192.168.1.4")
                    .receiverAddress(connectionDetails.hostname)
                    .setRequests(new String[]{RequestCompiler.encodeToString(new GameRequest())});

            sender.send(payload);
            System.out.println("Package sent: " + payload);
            System.out.println("Encoded as: " + PackageCompiler.encode(payload));
            System.out.println("Decoded as: " + PackageCompiler.decode(PackageCompiler.encode(payload)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.requestReceiver.expectRequest();
    }



}
