package gameServer.message.compiler;

import gameServer.gameEntities.PlayerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * A factory for compiling messages used in the Game Server
 * and Game Clients.
 */
public class MessageCompiler {

    public static List<String> joinGameMessage(String gameId, PlayerDetails player){
        return new ArrayList<String>(){{
            add("GAMEID");
            add(gameId);
            addAll(MessageCompiler.compilePlayerDetails(player));
            add("END");
        }};
    }

    public static List<String> logOnMessage(PlayerDetails player, int playerSpotsTotal){
        return new ArrayList<String>(){{
            add("LOGON");
            addAll(MessageCompiler.playerDetailsMessage(player));
            add("SPOTS");
            add(String.valueOf(playerSpotsTotal));
            add("END");
        }};
    }

    public static List<String> moveMessage(int[][] actions){
        List<String> output = new ArrayList<>();

        output.add("MOVE");
        output.add(String.valueOf(actions.length));
        output.add(String.valueOf(actions[0].length));

        for(int i = 0; i < actions.length; i++)
            for(int c = 0; c < actions[i].length; c++)
                output.add(String.valueOf(actions[i][c]));

        output.add("END");
        return output;
    }

    public static List<String> playerDetailsMessage(PlayerDetails player){
        List<String> output = new ArrayList<>();

        output.addAll(compilePlayerDetails(player));
        output.add("END");

        return output;
    }

    public static List<String> serverDetailsMessage(String ip, int port){
        return new ArrayList<String>(){{
            add(ip);
            add(String.valueOf(port));
            add("END");
        }};
    }

    public static List<String> gameFullMessage(){
        return new ArrayList<String>(){{
            add("FULL");
            add("END");
        }};
    }

    public static List<String> onlinePlayersList(List<PlayerDetails> onlinePlayers){
        List<String> output = new ArrayList<>();

        output.add("ONLINEPLAYERS");
        for(PlayerDetails playerDetails : onlinePlayers)
            output.addAll(playerDetailsMessage(playerDetails));

        output.add("END");

        return output;
    }

    public static List<String> leaveGameMessage(String gameId, PlayerDetails leavingPlayer){
        return new ArrayList<String>(){{
            add("LEAVE");
            add(gameId);
            addAll(MessageCompiler.compilePlayerDetails(leavingPlayer));
            add("END");
        }};
    }

    private static List<String> compilePlayerDetails(PlayerDetails player){
        List<String> output = new ArrayList<>();

        output.add("IP");
        output.add(player.connectionDetails.address);
        output.add("PORT");
        output.add(String.valueOf(player.connectionDetails.port));
        output.add("NAME");
        output.add(player.player.name);
        output.add("COLOR");
        output.add(String.valueOf(player.player.red));
        output.add(String.valueOf(player.player.green));
        output.add(String.valueOf(player.player.blue));

        return output;
    }

    public static String compileToLine(List<String> message){
        StringBuilder builder = new StringBuilder();
        for(String word : message)
            builder.append(word + "\0");

        return builder.toString();
    }
}
