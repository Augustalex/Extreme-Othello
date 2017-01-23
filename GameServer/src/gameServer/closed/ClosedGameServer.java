package gameServer.closed;

import gameServer.BroadcastChannel;
import gameServer.gameEntities.PlayerDetails;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2017-01-17.
 */
public class ClosedGameServer {

    public static final int serverPort = 2000;

    private List<PlayerDetails> playersDetails = new ArrayList<>();
    private List<ClosedGameClientHandler> playerConnections;
    private ClosedGame game;

    public ClosedGameServer(List<PlayerDetails> players){
        this.playersDetails = players;
    }

    public ClosedGameServer start(){
        try {
            List<Socket> playerConnections = establishPlayerConnections(playersDetails);

            this.game = new ClosedGame(
                    this.playersDetails,
                    getOutputStreamsFromClientConnections(playerConnections)
            );

            handlePlayerConnections(playerConnections);
        } catch (IOException e) {
            System.out.println("Could not open socket to current player.");
            e.printStackTrace();
        }

        return this;
    }

    private List<Socket> establishPlayerConnections(List<PlayerDetails> playersDetails) throws IOException {
        List<Socket> players = new ArrayList<>();

        ServerSocket server = new ServerSocket(serverPort);
        int nPlayers = playersDetails.size();
        int playersJoined = 0;
        while(playersJoined < nPlayers){
            Socket connection = server.accept();
            players.add(connection);
        }

        return players;
    }

    private OutputStream[] getOutputStreamsFromClientConnections(List<Socket> clientConnections){
        return clientConnections.stream().map(socket -> {
            try {
                return socket.getOutputStream();
            } catch (IOException e) {
                System.out.println("Could not retrieve OutputStream from a client.");
                e.printStackTrace();
                return null;
            }
        }).toArray(OutputStream[]::new);
    }

    private void handlePlayerConnections(List<Socket> playerConnections){
        playerConnections.forEach(socket -> {
            this.playerConnections.add(new ClosedGameClientHandler(socket, this.game));
        });
    }

    /**
     * Returns true if the ip and port belongs to a player registred in the
     * closed game.
     * @param ip
     * @param port
     * @return
     */
    public boolean isValidPlayer(String ip, int port){
        for(PlayerDetails player : playersDetails)
            if(player.connectionDetails.address.equals(ip)
                && player.connectionDetails.port == port)
                    return true;

        //No matches where found.
        return false;
    }

    /**
     * Search the list of registered playersDetails in the closed game
     * with the playersDetails connection details (IP and port number).
     *
     * If no match is found, null is returned. It is therefore recommended
     * to run a search with the isValidPlayer method before trying to retrieve
     * a PlayerDetails with this method.
     * @param ip
     * @param port
     * @return
     */
    private PlayerDetails getPlayerDetailsFromConnectionDetails(String ip, int port){
        for(PlayerDetails player : playersDetails)
            if(player.connectionDetails.address.equals(ip)
                    && player.connectionDetails.port == port)
                return player;

        //No player matched the received connection details.
        return null;
    }
}
