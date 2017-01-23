package communication;

import boardGameLibrary.players.Player;
import boardGameLibrary.players.RemotePlayer;
import gameServer.gameEntities.PlayerDetails;
import gameServer.message.compiler.MessageCompiler;
import hostDetails.Host;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Facade pattern class for easier message compilation from
 * game clients.
 *
 * Note that the reason for converting Client objects into the format
 * of the servers objects before parsing is simply because the parsers
 * were written wrongly and it what decided not to change them currently.
 *
 */
public class ClientMessageCompiler {

    /**
     * Message for logging on to a game server.
     * @param player
     * @param connectionDetails
     * @param playerSpotsTotal
     * @return
     */
    public static String logOnMessage(Host connectionDetails, Player player, int playerSpotsTotal){
        PlayerDetails playerDetails = ClientMessageCompiler.clientToServerPlayerConverter(player, connectionDetails);
        return MessageCompiler.compileToLine(
                MessageCompiler.logOnMessage(playerDetails, playerSpotsTotal)
        );
    }

    public static String joinGame(String gameId, Player player, Host connectionDetails){
        PlayerDetails playerDetails = ClientMessageCompiler.clientToServerPlayerConverter(player, connectionDetails);
        return MessageCompiler.compileToLine(
                MessageCompiler.joinGameMessage(gameId, playerDetails)
        );
    }

    /**
     * Converts a Client Player object together with a Host connection details object
     * into a Server side PlayerDetails object.
     * @param player
     * @param connectionDetails
     * @return
     */
    public static PlayerDetails clientToServerPlayerConverter(Player player, Host connectionDetails){
        PlayerDetails details = new PlayerDetails();
        details.connectionDetails = connectionDetails;

        Color color = player.getColor();
        details.player = new gameServer.gameEntities.Player()
                .setName(player.getName())
                .setRed((int)color.getRed())
                .setGreen((int)color.getGreen())
                .setBlue((int)color.getBlue());

        return details;
    }

    /**
     * Converts a Server PlayerDetails object into a Client RemotePlayer object.
     * Note that connection details are lost in this process as they are not a
     * part of the RemotePlayer object on the client side.
     * @param playerDetails
     * @return
     */
    public static Player serverToClientPlayerConverter(PlayerDetails playerDetails){
        return new RemotePlayer(playerDetails.player.name, playerDetails.player.getColor());
    }


}
