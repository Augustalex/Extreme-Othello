package communication;

import async.Delivery;
import async.PropertyDelivery;
import boardGameLibrary.players.Player;
import gameServer.gameEntities.PlayerDetails;
import gameServer.message.iterator.MessageIterator;
import gameServer.message.parser.IMessageParser;
import gameServer.open.message.parser.strategies.OnlinePlayersListParser;
import gameServer.open.message.parser.strategies.ServerDetailsMessageParser;
import hostDetails.Host;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 * A proxy for the client user to access information
 * from the server via a Java API.
 *
 * The API is synchronous and will block.
 *
 */
public class OpenGameDirectoryClientProxy{

    private Host currentGame = null;
    private List<PlayerDetails> playersOnline = null;

    private final MessageIterator messageIterator = new MessageIterator();
    private final ServerDetailsMessageParser serverDetailsMessageParser = new ServerDetailsMessageParser();
    private final OnlinePlayersListParser onlinePlayersListParser = new OnlinePlayersListParser();

    private static final class instanceHolder{
        public static final OpenGameDirectoryClientProxy instance = new OpenGameDirectoryClientProxy();
    }

    public static OpenGameDirectoryClientProxy get(){
        return instanceHolder.instance;
    }

    private OpenGameDirectoryClientProxy(){
        messageIterator.attachParsers(serverDetailsMessageParser, onlinePlayersListParser);
    }

    public Delivery<Host> joinGame(Player player, Host connectionDetails, String gameId) {
        Delivery<Host> result = new PropertyDelivery<>();

        new Thread(() -> {
            try{
                Socket socket = new Socket(getServerIP(), getServerPort());
                String message = ClientMessageCompiler.joinGame(gameId, player, connectionDetails);

                result.deliver(exchangeMessage(
                        message,
                        socket,
                        serverDetailsMessageParser
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        return result;
    }

    /**
     * Logs player in to the game server.
     * When a player logs in a new game is also registered on the
     * players computer. A number of available player spots is
     * therefore required in order to log in.
     *
     * When that is done the list of online players will be updated (as
     * this is the response from server upon login).
     *
     * @return
     */
    public Delivery<List<PlayerDetails>> logOnPlayer(Host connectionDetails, Player player, int playerSpots) {
        Delivery<List<PlayerDetails>> result = new PropertyDelivery<>();

        new Thread(() -> {
            try{
                Socket socket = new Socket(getServerIP(), getServerPort());
                String message = ClientMessageCompiler.logOnMessage(connectionDetails, player, playerSpots);

                result.deliver(exchangeMessage(
                        message,
                        socket,
                        onlinePlayersListParser
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        return result;
    }

    /**
     * Returns all players online given that the User is previously logged in.
     * @return
     */
    public List<PlayerDetails> getAllPlayersOnline() {
        throw new UnsupportedOperationException();
        //TODO implement this please... Must also be implemented on the server.
    }

    private String getServerIP(){
        return "10.10.107.76";
    }

    private int getServerPort(){
        return 1999;
    }

    /**
     * Given a parser it will use this class instance of MessageIterator
     * to process and validate a message response (to the message sent).
     *
     * @param message
     * @param connection
     * @param responseParser
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T> T exchangeMessage(String message, Socket connection, IMessageParser<T> responseParser) throws IOException {
        try{
            InputStream inputStream = sendMessage(message, connection);
            System.out.println("Sent message, now waiting for response.");
            messageIterator.setMessageInputStream(inputStream).nextInput();
            System.out.println("Got next response: " + messageIterator.getInput());
            responseParser.setInput(messageIterator.getInput());

            if(responseParser.isValid())
                return responseParser.parse();
            else
                throw new RuntimeException("Invalid message, cannot parse: " + responseParser.getInput());
        }
        finally{
            messageIterator.detachParsers(responseParser);
        }
    }

    private InputStream sendMessage(String message, Socket connection) throws IOException {
        PrintStream printStream = new PrintStream(connection.getOutputStream());
        printStream.println(message);
        printStream.flush();

        return connection.getInputStream();
    }
}
